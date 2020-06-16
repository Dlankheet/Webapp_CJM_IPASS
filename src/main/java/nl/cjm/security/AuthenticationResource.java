package nl.cjm.security;

import com.azure.core.annotation.Post;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import nl.cjm.model.MyUser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.AbstractMap.SimpleEntry;

import java.util.Calendar;

@Path("/authentication")
public class AuthenticationResource {
    final static public Key key = MacProvider.generateKey();

    private String createToken(String username, String role) throws JwtException{
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, 30);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration.getTime())
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUserByPassword(@FormParam("username") String username, @FormParam("password") String password){
        try{
            String role = MyUser.validateLogin(username, password);
            if(role==null) throw new IllegalArgumentException("No user found!");
            String token = createToken(username, role);
            SimpleEntry<String, String> JWT = new SimpleEntry<>("JWT", token);
            System.out.println("Er is een JWT token verzonden. Met de rol " + role);
            return Response.ok(JWT).build();
        }
        catch(JwtException | IllegalArgumentException e ){
            System.out.println("Er is een gebruiker niet geaccepteerd");
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}

