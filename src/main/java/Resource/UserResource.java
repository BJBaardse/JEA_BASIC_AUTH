package Resource;

import jwt.JWTTokenNeeded;
import jwt.Role;
import models.Userapp;
import rest.UserappDAO;


import javax.ejb.EJB;
import javax.ws.rs.*;
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

//    @GET
//    @Produces("application/json")
//    public Person test(){
//        Person berend = new Person("Berend", "Baardse");
//        userappDAO.save(berend);
//        return berend;
//
//    }

    @POST
    @Produces("application/json")
    public void save(Userapp person) {
        userappDAO.save(person);
    }

    @PUT
    @Consumes("application/json")
    public void update(Userapp person) {
        userappDAO.update(person);
    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public void delete(@PathParam("id") Long id) {
        Userapp person = userappDAO.find(id);
        userappDAO.delete(person);
    }
}

