package rest;

import models.Coupon;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CouponDAO {

    @PersistenceContext(unitName = "MySqlPool123")
    private EntityManager em;

    public List<Coupon> getAll() {
        return em.createNamedQuery("Coupon.getall", Coupon.class).getResultList();
    }

    public void add(Coupon coupon) {
        em.persist(coupon);
    }

    public void update(Coupon coupon) {
        em.merge(coupon);
    }

    public void swapState(int couponid) {
        Coupon coupon = em.createNamedQuery("Coupon.findOneID", Coupon.class).setParameter("id", couponid).getSingleResult();
        if(coupon.isValid() == true){
            coupon.setValid(false);
        }
        else{
            coupon.setValid(true);
        }
        em.merge(coupon);
    }
}
