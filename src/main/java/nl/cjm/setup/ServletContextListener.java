package nl.cjm.setup;

import nl.cjm.model.MyUser;
import nl.cjm.persistence.PersistenceManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce){
        new MyUser("Daniel", "iets12");
        try {
            PersistenceManager.loadWebsiteFromAzure();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Binnenhalen niet gelukt");
            e.printStackTrace();
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            PersistenceManager.saveWebsiteToAzure();
        } catch (IOException e) {
            System.out.println("Opslaan niet gelukt");
            e.printStackTrace();
        }
    }
}
