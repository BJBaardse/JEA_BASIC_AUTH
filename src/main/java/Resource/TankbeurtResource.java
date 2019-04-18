package Resource;

import models.Tankbeurt;
import rest.TankbeurtDAO;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tankbeurt")
public class TankbeurtResource {

    @EJB
    TankbeurtDAO tankbeurtDAO;

    @GET
    @Consumes("application/json")
    public List<Tankbeurt> getlist(){
        return tankbeurtDAO.getall();
    }
}
