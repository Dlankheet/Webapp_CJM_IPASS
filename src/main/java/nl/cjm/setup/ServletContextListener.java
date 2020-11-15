package nl.cjm.setup;

import nl.cjm.model.Contactblok;
import nl.cjm.model.MyUser;
import nl.cjm.model.Website;
import nl.cjm.persistence.PersistenceManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce){
        new MyUser("Daniel", "test123");
        new MyUser("Testaccount", "test123");
        //Un-comment this if you want to make blob-storage working again.
//        try {
//            PersistenceManager.loadWebsiteFromAzure();
//        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Binnenhalen niet gelukt");
//            e.printStackTrace();
//        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //Un-comment this if you want to make blob-storage working again.
//        try {
//            //PersistenceManager.saveWebsiteToAzure();
//        } catch (IOException e) {
//            System.out.println("Opslaan niet gelukt");
//            e.printStackTrace();
//        }
    }
}
