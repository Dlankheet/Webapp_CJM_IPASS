package nl.cjm.webservices;

import nl.cjm.webapp.model.GastBlok;
import nl.cjm.webapp.model.Website;
import nl.cjm.webapp.persistence.PersistenceManager;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Path("/contactaanvragen")
public class WebRecourse {
    Website website = Website.getWebsite();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContactverzoeken(){
        ArrayList<GastBlok> contactverzoeken = new ArrayList<>();
        contactverzoeken.addAll(website.getContactVerzoeken());
        return Response.ok(contactverzoeken).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response dienContactverzoekIn(@FormParam("name") String naam,
                                         @FormParam("fmail") String email, @FormParam("fnummer") int telefoon,
                                         @FormParam("ftitle") String titel, @FormParam("fnote") String beschrijving
                                            ){
        Date datum = new Date();
        GastBlok contactverzoek = new GastBlok(naam, email, telefoon, titel, beschrijving, datum);
        website.addContactverzoek(contactverzoek);
        try {
            PersistenceManager.saveWebsiteToAzure();
        } catch (IOException e) {
            System.out.println("Opslaan niet gelukt");
            e.printStackTrace();
        }
        return Response.ok(contactverzoek).build();
    }
}

