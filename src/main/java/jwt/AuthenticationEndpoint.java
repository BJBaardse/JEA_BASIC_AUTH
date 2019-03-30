package jwt;

import Resource.UserResource;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import models.Userapp;
import rest.UserappDAO;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("authentication")
public class AuthenticationEndpoint {
    @EJB
    private UserappDAO userappDAO;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("username") String username,
                                     @FormParam("password") String password) {

        try {

            // Authenticate the user using the credentials provided
            Userapp user = authenticate(username, password);

            // Issue a token for the user
            String token = issueToken(user);

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    private Userapp authenticate(String username, String password) throws Exception {
        return userappDAO.checkCreds(username, password);

        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
    }

    private String issueToken(Userapp user) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
        try {
            Algorithm algorithm = Algorithm.HMAC256("ikhaatfrontend");
            String token = JWT.create()
                    .withIssuer("Berend")
                    .withClaim("username",user.getUsername())
                    .withClaim("ID",user.getId())
                    .withClaim("Roles" , String.valueOf(user.getRole()))
                    .sign(algorithm);
            return token;


        }catch (Exception e){
            return "";
        }
    }
}