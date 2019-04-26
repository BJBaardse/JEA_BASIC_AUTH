package Resource;

import jwt.JWTTokenNeeded;
import jwt.Role;
import models.Coupon;
import rest.CouponDAO;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ejb.EJB;
import javax.management.Notification;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("coupon")
public class CouponResource {
    @EJB
    private CouponDAO couponDAO;

    @GET
    @Produces("application/json")
    public Response all(@Context UriInfo uriInfo) {

        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder())
                .rel("self").build();
        return Response.ok(couponDAO.getAll()).links(self).build();
    }

    @POST
    @Produces("application/json")
    public Response add(Coupon coupon, @Context UriInfo uriInfo){
        couponDAO.add(coupon);
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder())
                .rel("self").build();

        Link toevoegen = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()
                .path("afronden")
                .queryParam("id", "1"))
                .rel("swap state").build();
        return Response.status(Response.Status.ACCEPTED).links(self, toevoegen).build();
    }

    @POST
    @JWTTokenNeeded(Permissions = Role.Shell)
    @Path("/afronden")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response swapState(
            @FormParam("couponid") String id,
            @Context UriInfo uriInfo
    ){
        couponDAO.swapState(Integer.parseInt(id));
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder())
                .rel("self").build();
        return Response.status(Response.Status.ACCEPTED).links(self).build();
    }
    @PUT
    @Produces("application/json")
    public Response update(Coupon coupon, @Context UriInfo uriInfo){
        couponDAO.update(coupon);
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder())
                .rel("self").build();
        return Response.status(Response.Status.ACCEPTED).links(self).build();
    }
}
