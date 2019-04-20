package rest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import models.Brandstof;
import models.Coupon;
import models.Tankbeurt;
import models.Userapp;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Stateless
public class TankbeurtDAO {

    @PersistenceContext(unitName = "MySqlPool123")
    private EntityManager em;

    public List<Tankbeurt> getall() {
        return em.createNamedQuery("Tankbeurt.getall", Tankbeurt.class).getResultList();
    }

    public void createTankbeurt(int brandstofid, int liter, String token) {
        int userid = 0;
        try {
            userid = getuserid(token);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Brandstof brandstof = em.createNamedQuery("Brandstof.findone", Brandstof.class).setParameter("id", brandstofid).getSingleResult();
        Userapp user = em.createNamedQuery("Userapp.findOne", Userapp.class).setParameter("id", userid).getSingleResult();
        Tankbeurt beurt = new Tankbeurt(user, brandstof, liter);
        em.persist(beurt);
    }

    private int getuserid(String token) throws UnsupportedEncodingException {
        Claims claims =  Jwts.parser().setSigningKey("ikhaatfrontend".getBytes("UTF-8")).parseClaimsJws(token).getBody();
        return claims.get("userid", Integer.class);

    }

    public void createTankbeurtCoupon(int brandstofid, int liter, String couponstring, String token) {
        int userid = 0;
        try {
            userid = getuserid(token);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Brandstof brandstof = em.createNamedQuery("Brandstof.findone", Brandstof.class).setParameter("id", brandstofid).getSingleResult();
        Userapp user = em.createNamedQuery("Userapp.findOne", Userapp.class).setParameter("id", userid).getSingleResult();
        Coupon Coupon = em.createNamedQuery("Coupon.findOne", Coupon.class).setParameter("code", couponstring).getSingleResult();
        Tankbeurt beurt = new Tankbeurt(user, brandstof, Coupon, liter);
        em.persist(beurt);
    }
}
