package jwt;

//import Resource.UserResource;
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import models.Userapp;
import rest.UserappDAO;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

@Path("authentication")
public class AuthenticationEndpoint {
    @EJB
    private UserappDAO userappDAO;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("username") String username,
                                     @FormParam("password") String password,
                                     @FormParam("factor2") String factor2) {

        try {

            // Authenticate the user using the credentials provided
            Userapp user = authenticate(username, password);

            // Issue a token for the user
            String token = issueToken(user);
            if (user.getAuthenticationKey() == null) {
                return Response.ok(token).build();
            } else {
                if(decodeAuth(factor2, user) == true){
                    return Response.ok(token).build();
                }
                else{
                    return Response.status(Response.Status.FORBIDDEN).build();
                }
            }

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    private Userapp authenticate(String username, String password) throws Exception {
        return userappDAO.checkCreds(username, password);

        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
    }

    private boolean decodeAuth(String fa2, Userapp user) {
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        return gAuth.authorize(user.getAuthenticationKey(), Integer.valueOf(fa2)); //returns if valid

    }

    private String issueToken(Userapp user) throws UnsupportedEncodingException {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, 10);

        String jwt = Jwts.builder()
                .setSubject("users/TzMUocMF4p")
                .setExpiration(cal.getTime())
                .claim("userid", user.getId())
                .claim("username", user.getUsername())
                .claim("Role", String.valueOf(user.getRole()))
                .setIssuedAt(new Date())
                .signWith(
                        SignatureAlgorithm.HS256,
                        "ikhaatfrontend".getBytes("UTF-8")
                )
                .compact();

        return jwt;
    }
}