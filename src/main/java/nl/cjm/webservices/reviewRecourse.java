package nl.cjm.webservices;

import nl.cjm.model.Contactblok;
import nl.cjm.model.GastBlok;
import nl.cjm.model.Review;
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
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;

@Path("/pendingReviews")
public class reviewRecourse {
    Website website = Website.getWebsite();

    @GET
    @RolesAllowed("administrator")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPendingReviews(){
        ArrayList<GastBlok> reviews = new ArrayList<>();
        reviews.addAll(website.getPendingReviews());
        return Response.ok(reviews).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response dienReviewIn(@FormParam("fname1") String naam,
                                         @FormParam("fmail1") String email, @FormParam("fnummer1") String telefoon,
                                         @FormParam("frating1") int rating, @FormParam("fnote1") String onderbouwing
    ){
        Date datumincijfers = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String datum = formatter.format(datumincijfers);
        if(naam == null || email == null || telefoon == null || rating == 0 || onderbouwing == null ){
            return Response.status(Response.Status.BAD_REQUEST).build();}
        else{
            Review review = new Review(naam, email, telefoon, onderbouwing, datum, rating);
                try {
                    website.addPendingReview(review);
                    PersistenceManager.saveWebsiteToAzure();
                    emailRecourse.sendReviewMail(review);
                    return Response.ok(review).build();
                } catch (IOException | IllegalArgumentException fout) {
                    fout.printStackTrace();
                    return Response.status(Response.Status.BAD_REQUEST).entity(new AbstractMap.SimpleEntry<String,String>("ErrorMessage", fout.getMessage())).build();
                }

            }
    }
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("administrator")
    @Path("{datum}")
    public Response deleteReview(@Context SecurityContext securityContext, @PathParam("datum") String datum) {
        for (Review review : website.getPendingReviews()) {
            String date = review.getDatum();
            date = date.replaceAll(" ", "_");
            System.out.println(date);
            System.out.println(datum);
            if (date.equals(datum)) {
                try {
                    website.removeReview(review);
                    return Response.ok().build();
                } catch (Exception e) {
                    return Response.status(Response.Status.NOT_FOUND).build();
                }
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

