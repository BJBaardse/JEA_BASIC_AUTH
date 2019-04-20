package Resource;

import models.Tankbeurt;
import rest.TankbeurtDAO;

import javax.ejb.EJB;
import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.*;
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

    @POST
    @Path("/createnocoupon")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postTankbeurt(
            @FormParam("brandstof") String Brandstofid,
            @FormParam("liter") String liter,
            @FormParam("token") String token
    ) {
        tankbeurtDAO.createTankbeurt(Integer.parseInt(Brandstofid), Integer.parseInt(liter), token);
        return Response.status(200).build();
    }
    @POST
    @Path("/createwithcoupon")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postTankbeurtCoupon(
            @FormParam("brandstof") String Brandstofid,
            @FormParam("liter") String liter,
            @FormParam("coupon") String coupon,
            @FormParam("token") String token
    ) {
        tankbeurtDAO.createTankbeurtCoupon(Integer.parseInt(Brandstofid), Integer.parseInt(liter), coupon, token);
        return Response.status(200).build();
    }

}
