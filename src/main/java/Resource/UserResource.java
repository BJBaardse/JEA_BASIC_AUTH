package Resource;

import jwt.JWTTokenNeeded;
import jwt.Role;
import models.Userapp;
import rest.UserappDAO;


import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;
import java.util.List;

//https://blog.payara.fish/securing-a-rest-service
@Path("Userapp")
public class UserResource {
    //@Inject
    @EJB
    private UserappDAO userappDAO;


    @GET
    @JWTTokenNeeded(Permissions = Role.Shell)
    @Produces("application/json")
    public List<Userapp> all() {
        return userappDAO.getAll();
    }



    @GET
    @JWTTokenNeeded(Permissions = Role.User)
    @Path("/allstations")
    @Produces
    public List<Userapp> getStations(){
        return userappDAO.getStations();
    }

    @POST
    @JWTTokenNeeded(Permissions = Role.Shell)
    @Produces("application/json")
    public void save(Userapp person) {
        userappDAO.save(person);
    }

    @GET
    @Path("/keys")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public String generateAuthkey(ContainerRequestContext requestContext){
        return userappDAO.generateAuthKey(requestContext);
    }

    @PUT
    @Consumes("application/json")
    public void update(Userapp person) {
        userappDAO.update(person);
    }

//    @DELETE
////    @Path("/{id}")
////    @Consumes("application/json")
////    public void delete(@PathParam("id") Long id) {
////        Userapp person = userappDAO.find(id);
////        userappDAO.delete(person);
////    }
}

