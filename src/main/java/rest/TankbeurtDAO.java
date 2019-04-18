package rest;

import models.Tankbeurt;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TankbeurtDAO {

    @PersistenceContext(unitName = "MySqlPool123")
    private EntityManager em;

    public List<Tankbeurt> getall() {
        return em.createNamedQuery("Tankbeurt.getall", Tankbeurt.class).getResultList();
    }
}
