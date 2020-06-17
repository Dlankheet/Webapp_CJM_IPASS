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
        new MyUser("Daniel", "iets12");
        new MyUser("Testaccount", "test123");
        try {
            PersistenceManager.loadWebsiteFromAzure();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Binnenhalen niet gelukt");
            e.printStackTrace();
        }
        Website website = Website.getWebsite();
        website.addContactverzoek(new Contactblok("Extra", "test.test@test.nl", 612345678, "Testverzoek", "Ik wil een contact", "12-07-2000"));
        website.addContactverzoek(new Contactblok("Extra2", "test.test@test.nl", 612345678, "Testverzoek2", "Ik wil een contact2", "12-07-2010"));
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
