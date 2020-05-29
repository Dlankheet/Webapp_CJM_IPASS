package nl.cjm.setup;
// Voor deze iteratie(2) zal de data alleen opgeslagen worden op het moment dat deze afsluit en ingeladen als de server start.
// Dit zal bij verdere iteraties uitgewerkt worden;

import nl.cjm.webapp.persistence.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce){
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
