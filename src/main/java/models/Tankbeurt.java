package models;

import models.Brandstof;
import models.Userapp;
import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.xml.registry.infomodel.User;
@Entity
@NamedQueries({
        @NamedQuery(name = "Tankbeurt.getall", query = "select p from Tankbeurt p")
}
)
public class Tankbeurt {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLiter() {
        return liter;
    }

    public void setLiter(int liter) {
        this.liter = liter;
    }

    public Brandstof getBrandstof() {
        return brandstof;
    }

    public void setBrandstof(Brandstof brandstof) {
        this.brandstof = brandstof;
    }

    public Userapp getUser() {
        return user;
    }

    public void setUser(Userapp user) {
        this.user = user;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    @Id
    @GeneratedValue
    int id;

    @Min(0)
    @NotNull
    int liter;
    @ManyToOne
    @NotNull
    Brandstof brandstof;
    @ManyToOne
    @NotNull
    Userapp user;
    @ManyToOne
    Coupon coupon;
    @Min(0)
    double prijs;

    public Tankbeurt(){

    }
    public Tankbeurt(Userapp user, Brandstof brandstof,Coupon coupon, int liter){
        this.user = user;
        this.coupon = coupon;
        this.liter = liter;
        this.brandstof = brandstof;
        calculatePrijs();
    }
    public Tankbeurt(Userapp user, Brandstof brandstof,  int liter){
        this.user = user;
        this.liter = liter;
        this.brandstof = brandstof;
        calculatePrijs();
    }
    private void calculatePrijs(){
        if(coupon == null){
            prijs = liter * brandstof.prijs;
        }
        else{
            prijs = (liter * brandstof.prijs)*((100- coupon.percentOff)/100);
        }
    }

}
