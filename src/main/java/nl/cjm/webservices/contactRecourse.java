package nl.cjm.webservices;

import nl.cjm.model.Contactblok;
import nl.cjm.model.GastBlok;
import nl.cjm.model.Website;
import nl.cjm.persistence.PersistenceManager;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Path("/contactaanvragen")
public class contactRecourse {
    Website website = Website.getWebsite();

    @GET
    @RolesAllowed("administrator")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContactverzoeken() {
        ArrayList<Contactblok> contactverzoeken = new ArrayList<>();
        contactverzoeken.addAll(website.getContactVerzoeken());
        return Response.ok(contactverzoeken).build();
    }

    @POST //In resource checken maar ook in domein.
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response dienContactverzoekIn(@FormParam("fname") String naam,
                                         @FormParam("fmail") String email, @FormParam("fnummer") int telefoon,
                                         @FormParam("ftitle") String titel, @FormParam("fnote") String beschrijving
    ) {
        Date datuminnummers = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String datum = formatter.format(datuminnummers);
        if (naam == null || email == null || telefoon == 0 || titel == null || beschrijving == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            Contactblok contactverzoek = new Contactblok(naam, email, telefoon, titel, beschrijving, datum);
            if (website.getContactVerzoeken().contains(contactverzoek)) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            } else {
                website.addContactverzoek(contactverzoek);
                try {
                    PersistenceManager.saveWebsiteToAzure();
                    emailRecourse.sendContactMail(contactverzoek);
                } catch (IOException e) {
                    System.out.println("Opslaan niet gelukt");
                    e.printStackTrace();
                }
                //emailRecourse.sendMail(email, titel, beschrijving);
                return Response.ok(contactverzoek).build();
            }
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("administrator")
    @Path("{datum}")
    public Response deleteContactVerzoek(@Context SecurityContext securityContext, @PathParam("datum") String datum) {
        for (Contactblok contact : website.getContactVerzoeken()) {
            String date = contact.getDatum();
            date = date.replaceAll(" ", "_");
            System.out.println(date);
            System.out.println(datum);
            if (date.equals(datum)) {
                try {
                    website.removeContactverzoek(contact);
                    return Response.ok().build();
                } catch (Exception e) {
                    return Response.status(Response.Status.NOT_FOUND).build();
                }
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{datum}")
    public Response getContactverzoek(@Context SecurityContext securityContext, @PathParam("datum") String datum) {
        for (Contactblok contact : website.getContactVerzoeken()) {
            String date = contact.getDatum();
            date = date.replaceAll(" ", "_");
            System.out.println(date);
            System.out.println(datum);
            if (date.equals(datum)) {
                try {
                    return Response.ok(contact).build();
                } catch (Exception e) {
                    return Response.status(Response.Status.NOT_FOUND).build();
                }
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

