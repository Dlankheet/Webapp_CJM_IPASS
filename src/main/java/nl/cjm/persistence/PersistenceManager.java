package nl.cjm.persistence;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import nl.cjm.model.Website;

import java.io.*;

public class PersistenceManager {
    private final static String ENDPOINT = "https://cjmwebapp.blob.core.windows.net/";
    private final static String SASTOKEN = "?sv=2019-10-10&ss=b&srt=co&sp=rwdlacx&se=2020-08-04T04:11:48Z&st=2020-06-08T20:11:48Z&spr=https&sig=DaxJgLkPi4MQ7HuCSwIg7IhH2OjTo6HiCE%2BTkOmc0do%3D";
    private final static String CONTAINER = "websitecontainer";
    private static BlobContainerClient blobContainer = new BlobContainerClientBuilder().endpoint(ENDPOINT).sasToken(SASTOKEN).containerName(CONTAINER).buildClient();
    public static void loadWebsiteFromAzure() throws IOException, ClassNotFoundException {
        System.out.println("Blob wordt ingeladen;");
        if(blobContainer.exists()) {
            BlobClient blob = blobContainer.getBlobClient("website_blob");
            if(blob.exists()){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                blob.download(baos);

                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);

                Object obj = ois.readObject();
                if(obj instanceof Website){
                    Website loadedwebsite = (Website) obj ;
                    Website.setWebsite(loadedwebsite);
                    System.out.println(loadedwebsite.getContactVerzoeken());
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
        BlobClient blob = blobContainer.getBlobClient("website_blob");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(Website.getWebsite());

        byte[] bytez = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
        blob.upload(bais, bytez.length, true);
        System.out.println("Website opgeslagen");

        baos.close();
        oos.close();
        bais.close();
    }
}
