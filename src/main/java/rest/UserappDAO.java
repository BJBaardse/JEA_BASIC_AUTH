package rest;


import models.Userapp;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class UserappDAO {

    @PersistenceContext (unitName = "MySqlPool123")
    private EntityManager entityManager;

    public List<Userapp> getAll() {
        return entityManager.createNamedQuery("Userapp.getAll", Userapp.class).getResultList();
    }

    public Userapp find(Long id) {
        return entityManager.createNamedQuery("Userapp.findOne", Userapp.class).setParameter("id", id).getSingleResult();
    }

    public void save(Userapp person) {
        entityManager.persist(person);
    }

    public void update(Userapp person) {
        entityManager.merge(person);
    }

    public void delete(Userapp person) {
        entityManager.remove(person);
    }
}

