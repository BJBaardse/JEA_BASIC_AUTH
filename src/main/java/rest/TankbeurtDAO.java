package rest;

import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import models.Brandstof;
import models.Coupon;
import models.Tankbeurt;
import models.Userapp;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.container.ContainerRequestContext;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Stateless
public class TankbeurtDAO {

    @PersistenceContext(unitName = "MySqlPool123")
    private EntityManager em;

    public List<Tankbeurt> getall() {
        return em.createNamedQuery("Tankbeurt.getall", Tankbeurt.class).getResultList();
    }

    public void createTankbeurt(int brandstofid, int tankstationid,int liter, String token) {
        int userid = 0;
        try {
            userid = getuserid(token);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Brandstof brandstof = em.createNamedQuery("Brandstof.findone", Brandstof.class).setParameter("id", brandstofid).getSingleResult();
        Userapp user = em.createNamedQuery("Userapp.findOne", Userapp.class).setParameter("id", userid).getSingleResult();
        Userapp tankstation = em.createNamedQuery("Userapp.findOne", Userapp.class).setParameter("id", tankstationid).getSingleResult();
        Tankbeurt beurt = new Tankbeurt(user, tankstation, brandstof, liter);
        em.persist(beurt);
    }

    private int getuserid(String token) throws UnsupportedEncodingException {
        Claims claims =  Jwts.parser().setSigningKey("ikhaatfrontend".getBytes("UTF-8")).parseClaimsJws(token).getBody();
        return claims.get("userid", Integer.class);

    }

    public void createTankbeurtCoupon(int brandstofid, int tankstationid,int liter, String couponstring, String token) {
        int userid = 0;
        try {
            userid = getuserid(token);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Brandstof brandstof = em.createNamedQuery("Brandstof.findone", Brandstof.class).setParameter("id", brandstofid).getSingleResult();
        Userapp user = em.createNamedQuery("Userapp.findOne", Userapp.class).setParameter("id", userid).getSingleResult();
        Userapp tankstation = em.createNamedQuery("Userapp.findOne", Userapp.class).setParameter("id", tankstationid).getSingleResult();
        Coupon Coupon = em.createNamedQuery("Coupon.findOne", Coupon.class).setParameter("code", couponstring).getSingleResult();
        Tankbeurt beurt = new Tankbeurt(user, tankstation,brandstof, Coupon, liter);
        em.persist(beurt);
    }

    public List<Tankbeurt> getOpenOrders(ContainerRequestContext requestContext) {
        // Validate the token
        int userid;
        try {
            String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
            String token = authorizationHeader.substring("Bearer".length()).trim();
            Claims claims = Jwts.parser().setSigningKey("ikhaatfrontend".getBytes("UTF-8")).parseClaimsJws(token).getBody();
            userid = claims.get("userid", Integer.class);
            return em.createNamedQuery("Tankbeurt.getopenorders", Tankbeurt.class).setParameter("id", userid).getResultList();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
