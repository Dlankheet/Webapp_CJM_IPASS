package nl.cjm.webservices;

import nl.cjm.model.Contactblok;
import nl.cjm.model.GastBlok;
import nl.cjm.model.Review;
import nl.cjm.model.Website;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;

@Path("/acceptedReviews")
public class reviewGoedkeuren {
    Website website = Website.getWebsite();

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("administrator")
    @Path("{datum}")
    public Response acceptReview(@Context SecurityContext securityContext, @PathParam("datum") String datum) {
        for (Review review : website.getPendingReviews()) {
            String date = review.getDatum();
            date = date.replaceAll(" ", "_");
            System.out.println(date);
            System.out.println(datum);
            if (date.equals(datum)) {
                try {
                    website.acceptReview(review);
                    return Response.ok().build();
                } catch (Exception e) {
                    System.out.println(e);
                    return Response.status(Response.Status.NOT_FOUND).build();
                }
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getacceptedReviews(@Context SecurityContext securityContext) {
        ArrayList<Review> reviews = new ArrayList<>();
        reviews.addAll(website.getGeaccepteerdeReviews());
        return Response.ok(reviews).build();
    }
}

