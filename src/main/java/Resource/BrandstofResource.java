package Resource;

import jwt.JWTTokenNeeded;
import jwt.Role;
import models.Brandstof;
import rest.BrandstofDao;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("brandstof")
public class BrandstofResource {

    @EJB
    private BrandstofDao brandstofDao;

    @GET
    @Produces("application/json")
    public List<Brandstof> all() {
        return brandstofDao.getAll();
    }

    @POST
    @JWTTokenNeeded(Permissions = Role.Shell)
    @Produces("application/json")
    public Response addBrandstof(Brandstof brandstof){
        brandstofDao.add(brandstof);
        return Response.status(200).build();
    }

    @PUT
    @JWTTokenNeeded(Permissions = Role.Shell)
    @Produces("application/json")
    public Response update(Brandstof brandstof){
        brandstofDao.update(brandstof);
        return Response.status(200).build();

    }

}
