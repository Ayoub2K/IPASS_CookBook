package CookBook.webservices;

import CookBook.security.LoginFilter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.HashMap;

@Path("login")
public class LoginResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response whoAmI(@Context SecurityContext ctx){
        Principal loggedInUser = ctx.getUserPrincipal();
        System.out.println("login get");
        if(loggedInUser == null){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        HashMap<String, String> response = new HashMap<>();
        response.put("user", loggedInUser.getName());
        return Response.ok(response).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("name") String username, @FormParam("password") String password){
        HashMap<String, String> response = new HashMap<>();
        System.out.println("login post");

        if(username.equals("admin") && password.equals("admin")){
            response.put("user", username);
            String token = Jwts.builder()
                    .setSubject("admin")
                    .signWith(SignatureAlgorithm.HS512, LoginFilter.key)
                    .compact();
            response.put("token", token);
            return Response.ok(response).build();
        }

        response.put("error", "Foute inloggegevens");
        return Response.status(Response.Status.CONFLICT).entity(response).build();
    }
}
