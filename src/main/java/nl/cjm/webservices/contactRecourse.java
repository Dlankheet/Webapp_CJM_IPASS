package nl.cjm.webservices;

import nl.cjm.webapp.model.Contactblok;
import nl.cjm.webapp.model.GastBlok;
import nl.cjm.webapp.model.Website;
import nl.cjm.webapp.persistence.PersistenceManager;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Path("/contactaanvragen")
public class contactRecourse {
    Website website = Website.getWebsite();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContactverzoeken(){
        ArrayList<Contactblok> contactverzoeken = new ArrayList<>();
        contactverzoeken.addAll(website.getContactVerzoeken());
        return Response.ok(contactverzoeken).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response dienContactverzoekIn(@FormParam("fname") String naam,
                                         @FormParam("fmail") String email, @FormParam("fnummer") int telefoon,
                                         @FormParam("ftitle") String titel, @FormParam("fnote") String beschrijving
                                            ){
        Date datuminnummers = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String datum = formatter.format(datuminnummers);
        if(naam == null || email == null || telefoon == 0 || titel == null || beschrijving == null ){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            Contactblok contactverzoek = new Contactblok(naam, email, telefoon, titel, beschrijving, datum);
            if(website.getContactVerzoeken().contains(contactverzoek)){
                return Response.status(Response.Status.BAD_REQUEST).build();
            }else{
                website.addContactverzoek(contactverzoek);
                System.out.println(naam + email + telefoon + titel + beschrijving);
                try {
                    PersistenceManager.saveWebsiteToAzure();
                } catch (IOException e) {
                    System.out.println("Opslaan niet gelukt");
                    e.printStackTrace();
                }
                //emailRecourse.sendMail(email, titel, beschrijving);
                return Response.ok(contactverzoek).build();
            }
        }
    }
}

