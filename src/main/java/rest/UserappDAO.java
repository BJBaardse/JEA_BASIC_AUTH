package rest;


import com.google.common.net.HttpHeaders;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import models.Userapp;
import sun.rmi.runtime.Log;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.container.ContainerRequestContext;
import javax.xml.registry.infomodel.User;
import java.io.UnsupportedEncodingException;
import java.util.List;


@Stateless
public class UserappDAO {

    @PersistenceContext (unitName = "MySqlPool123")
    private EntityManager entityManager;

    public List<Userapp> getAll() {
        return entityManager.createNamedQuery("Userapp.getAll", Userapp.class).getResultList();
    }

    public Userapp find(int id) {
        return entityManager.createNamedQuery("Userapp.findOne", Userapp.class).setParameter("id", id).getSingleResult();
    }

    public Userapp checkCreds(String username, String password){
        return entityManager.createNamedQuery("Userapp.checkcreds", Userapp.class).setParameter("username", username).setParameter("password",password).getSingleResult();
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

    public List<Userapp> getStations() {
        return entityManager.createNamedQuery("Userapp.findStations", Userapp.class).getResultList();
    }
    public String generateAuthKey(ContainerRequestContext requestContext){
        Claims claims = null;
        try {
            String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
            String token = authorizationHeader.substring("Bearer".length()).trim();
            claims = Jwts.parser().setSigningKey("ikhaatfrontend".getBytes("UTF-8")).parseClaimsJws(token).getBody();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int user = claims.get("userid", Integer.class);
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        final GoogleAuthenticatorKey key = gAuth.createCredentials();
        Userapp userapp =  find(user);
        userapp.setAuthenticationKey(key.getKey());
        entityManager.merge(userapp);
        return key.getKey();
    }
}

