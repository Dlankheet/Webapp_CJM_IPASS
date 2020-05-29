package nl.cjm.webservices;

import nl.cjm.webapp.*;
import nl.cjm.webapp.model.GastBlok;
import nl.cjm.webapp.model.Website;

import javax.json.*;
import javax.ws.rs.*;

@Path("/contactaanvragen")
public class WebRecourse {
    Website website = Website.getWebsite();

    @GET
    @Produces("application/json")
    public String getCountries(){
        JsonArrayBuilder jab = Json.createArrayBuilder();
        JsonObjectBuilder job = Json.createObjectBuilder();

        for(GastBlok contact : website.getContactVerzoeken()){
            job.add("Contactaanvraag", contact.toString());
            jab.add(job);
        }
        return jab.build().toString();
    }
}

