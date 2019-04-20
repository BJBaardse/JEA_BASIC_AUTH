package models;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;

import javax.enterprise.inject.Default;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;
@Entity
@NamedQueries({
        @NamedQuery(name = "Coupon.getall", query = "select p from Coupon p"),
        @NamedQuery(name = "Coupon.findOne", query = "select p from Coupon p where p.couponCode = :code")

}
)
public class Coupon {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public int getAllowed() {
        return allowed;
    }

    public void setAllowed(int allowed) {
        this.allowed = allowed;
    }

    public double getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(double percentOff) {
        this.percentOff = percentOff;
    }

    @Id
    @GeneratedValue
    int id;

    String couponCode;
    @ColumnDefault("True")
    boolean isValid;

    @ColumnDefault("0")
    int used;
    @Min(1)
    @NotNull
    int allowed;

    @Min(0)
    @Max(100)
    @NotNull
    double percentOff;
    public Coupon(){

    }
    public Coupon(String code, int allowed, double percent){
        couponCode = code;
        this.allowed = allowed;
        percentOff = percent;
    }


}
