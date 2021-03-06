package nl.cjm.webservices;

import nl.cjm.model.Contactblok;
import nl.cjm.model.Foto;
import nl.cjm.model.Website;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;

@Path("/elektraslider")
public class ElektraSliderRecource {
    Website website = Website.getWebsite();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFotos() {
        ArrayList<Foto> fotos = new ArrayList<>();
        fotos.addAll(website.getFotosliderElektra());
        return Response.ok(fotos).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @RolesAllowed("administrator")
    public Response addFoto(@FormParam("titel") String titel, @FormParam("beschrijving") String beschrijving,
                            @FormParam("fotoBase64") String foto) {
        try{
            Foto fotoObject = new Foto(foto, titel, beschrijving);
            website.addElektraFoto(fotoObject);
            return Response.ok(fotoObject).build();
        }catch(Exception e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("administrator")
    @Path("{titel}")
    public Response deleteFotoItem(@Context SecurityContext securityContext, @PathParam("titel") String titel) {
        for (Foto foto : website.getFotosliderElektra()) {
            String fotoTitel = foto.getTitel();
            fotoTitel = fotoTitel.replaceAll(" ", "_");
            if (fotoTitel.equals(titel)) {
                try {
                    website.removeFotoElektra(foto);
                    return Response.ok().build();
                } catch (Exception e) {
                    return Response.status(Response.Status.NOT_FOUND).build();
                }
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
