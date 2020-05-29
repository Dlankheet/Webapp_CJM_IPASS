package nl.cjm.webapp.persistence;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import nl.cjm.webapp.model.Website;

import java.io.*;

public class PersistenceManager {
    private final static String ENDPOINT = "https://cjmwebapp.dfs.core.windows.net/";
    private final static String SASTOKEN = "?sv=2019-10-10&ss=b&srt=co&sp=rwdlacx&se=2021-05-31T02:04:03Z&st=2020-05-28T18:04:03Z&spr=https&sig=QSjThmo3H%2F5tvarrSg1YMcbRqxQR0MRqbevvXevwjzE%3D";
    private final static String CONTAINER = "websitecontainer";
    private static BlobContainerClient blobContainer = new BlobContainerClientBuilder().endpoint(ENDPOINT).sasToken(SASTOKEN).containerName(CONTAINER).buildClient();
    public static void loadWebsiteFromAzure() throws IOException, ClassNotFoundException {
        if(blobContainer.exists()) {
            BlobClient blob = blobContainer.getBlobClient("world_blob");
            if(blob.exists()){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                blob.download(baos);

                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);

                Object obj = ois.readObject();
                if(obj instanceof Website){
                    Website loadedwebsite = (Website) obj ;
                    Website.setWebsite(loadedwebsite);
                }
                baos.close();
                ois.close();
            }
        }
    }
    public static void saveWebsiteToAzure() throws IOException {
        if(!blobContainer.exists()) {
            blobContainer.create();
        }
        BlobClient blob = blobContainer.getBlobClient("world_blob");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(Website.getWebsite());

        byte[] bytez = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
        blob.upload(bais, bytez.length, true);
        System.out.println("World opgeslagen");

        baos.close();
        oos.close();
        bais.close();
    }
}
