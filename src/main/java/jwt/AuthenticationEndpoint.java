package jwt;

//import Resource.UserResource;
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
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
    public Userapp checktoken(String token){
        return new Userapp("Berends", "password", Role.Admin);
    }
    private String issueToken(Userapp user) throws UnsupportedEncodingException {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, 90);
//        try {
//            Algorithm algorithm = Algorithm.HMAC256("ikhaatfrontend");
//            String token = JWT.create()
//                    .withIssuer("Berend")
//                    .withClaim("username",user.getUsername())
//                    .withClaim("ID",user.getId())
//                    .withClaim("Roles" , String.valueOf(user.getRole()))
//                    .withExpiresAt(cal.getTime())
//                    .withIssuedAt(new Date())
//                    .sign(algorithm);
//            return token;
//        }
        String jwt = Jwts.builder()
                .setSubject("users/TzMUocMF4p")
                .setExpiration(cal.getTime())
                .claim("username", user.getUsername())
                .claim("ID", user.getId())
                .claim("Role", String.valueOf(user.getRole()))
                .setIssuedAt(new Date())
                .signWith(
                        SignatureAlgorithm.HS256,
                        "ikhaatfrontend".getBytes("UTF-8")
                )
                .compact();

        return jwt;
//        catch (Exception e){
//            return "";
//        }
    }
}