package Resource;

import models.Coupon;
import rest.CouponDAO;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ejb.EJB;
import javax.management.Notification;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("coupon")
public class CouponResource {
    @EJB
    private CouponDAO couponDAO;

    @GET
    @Produces("application/json")
    public List<Coupon> all() {
        return couponDAO.getAll();
    }

    @POST
    @Produces("application/json")
    public Response add(Coupon coupon){
        couponDAO.add(coupon);
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @PUT
    @Produces("application/json")
    public Response update(Coupon coupon){
        couponDAO.update(coupon);
        return Response.status(Response.Status.ACCEPTED).build();
    }
}
