package rest;

import models.Brandstof;
import models.Userapp;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
public class BrandstofDao {
    @PersistenceContext(unitName = "MySqlPool123")
    private EntityManager entityManager;


    public List<Brandstof> getAll() {
        return entityManager.createNamedQuery("Brandstof.getall", Brandstof.class).getResultList();
    }
    public Response add(Brandstof brandstof){
        entityManager.persist(brandstof);
        return Response.status(200).build();

    }

    public void update(Brandstof brandstof) {
        entityManager.merge(brandstof);
    }

    public void delete(Brandstof brandstof) {
        entityManager.remove(brandstof);
    }
}
