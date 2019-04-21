package Resource;

import jwt.JWTTokenNeeded;
import jwt.Role;
import models.Tankbeurt;
import rest.TankbeurtDAO;

import javax.ejb.EJB;
import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tankbeurt")
public class TankbeurtResource {

    @EJB
    TankbeurtDAO tankbeurtDAO;

    @GET
    @Consumes("application/json")
    public List<Tankbeurt> getlist() {
        return tankbeurtDAO.getall();
    }
    @GET
    @JWTTokenNeeded(Permissions = Role.Shell)
    @Path("/closedorders")
    @Consumes("application/json")
    public List<Tankbeurt> getclosedorders(){
        return tankbeurtDAO.getclosedorders();
    }
    @GET
    @JWTTokenNeeded(Permissions = Role.Tankstation)
    @Path("/openorders")
    @Consumes("application/json")
    public List<Tankbeurt> getopenorders(ContainerRequestContext requestContext){
        return tankbeurtDAO.getOpenOrders(requestContext);
    }

    @POST
    @JWTTokenNeeded(Permissions = Role.Tankstation)
    @Path("/afronden")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response beurtbetalen(
            @FormParam("tankbeurtid") int tankbeurtid
    ){
        tankbeurtDAO.beurtbetalen(tankbeurtid);
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @POST
    @JWTTokenNeeded(Permissions = Role.User)
    @Path("/createnocoupon")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postTankbeurt(
            @FormParam("brandstof") String Brandstofid,
            @FormParam("liter") String liter,
            @FormParam("token") String token,
            @FormParam("tankstation") String tankstationid
    ) {
        tankbeurtDAO.createTankbeurt(Integer.parseInt(Brandstofid), Integer.parseInt(tankstationid), Integer.parseInt(liter), token);
        return Response.status(200).build();
    }
    @POST
    @JWTTokenNeeded(Permissions = Role.User)
    @Path("/createwithcoupon")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postTankbeurtCoupon(
            @FormParam("brandstof") String Brandstofid,
            @FormParam("liter") String liter,
            @FormParam("coupon") String coupon,
            @FormParam("token") String token,
            @FormParam("tankstation") String tankstationid
    ) {
        return tankbeurtDAO.createTankbeurtCoupon(Integer.parseInt(Brandstofid), Integer.parseInt(tankstationid),Integer.parseInt(liter), coupon, token);
    }

}
