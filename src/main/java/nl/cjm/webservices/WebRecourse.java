package nl.cjm.webservices;

import nl.cjm.webapp.model.GastBlok;
import nl.cjm.webapp.model.Website;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/contactaanvragen")
public class WebRecourse {
    Website website = Website.getWebsite();

    @GET
    @Produces("application/json")
    public Response getContactverzoek(){
        JsonArrayBuilder jab = Json.createArrayBuilder();
        JsonObjectBuilder job = Json.createObjectBuilder();

        for(GastBlok contact : website.getContactVerzoeken()){
            job.add("Contactaanvraag", contact.toString());
            jab.add(job);
        }
        return jab.build().toString();
    }
}

