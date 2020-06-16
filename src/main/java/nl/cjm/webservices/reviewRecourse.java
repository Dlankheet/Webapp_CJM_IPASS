package nl.cjm.webservices;

import nl.cjm.model.GastBlok;
import nl.cjm.model.Review;
import nl.cjm.model.Website;
import nl.cjm.persistence.PersistenceManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Path("/pendingReviews")
public class reviewRecourse {
    Website website = Website.getWebsite();

    @GET
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
                                         @FormParam("fmail1") String email, @FormParam("fnummer1") int telefoon,
                                         @FormParam("frating1") int rating, @FormParam("fnote1") String onderbouwing
    ){
        Date datumincijfers = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String datum = formatter.format(datumincijfers);
        if(naam == null || email == null || telefoon == 0 || rating == 0 || onderbouwing == null ){
            return Response.status(Response.Status.BAD_REQUEST).build();}
        else{
            Review review = new Review(naam, email, telefoon, onderbouwing, datum, rating);
            if(website.getPendingReviews().contains(review)){
                return Response.status(Response.Status.BAD_REQUEST).build();
            } else{
                website.addPendingReview(review);
                System.out.println(naam + email + telefoon + rating + onderbouwing);
                System.out.println(review);
                try {
                    PersistenceManager.saveWebsiteToAzure();
                } catch (IOException e) {
                    System.out.println("Opslaan niet gelukt");
                    e.printStackTrace();
                }
                //emailRecourse.sendMail(email, titel, beschrijving);
                return Response.ok(review).build();
            }
    }}
}

