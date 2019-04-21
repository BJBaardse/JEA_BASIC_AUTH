package models;

import models.Brandstof;
import models.Userapp;

import javax.enterprise.inject.Default;
import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.ws.rs.DefaultValue;
import javax.xml.registry.infomodel.User;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "Tankbeurt.getall", query = "select p from Tankbeurt p"),
        @NamedQuery(name = "Tankbeurt.getopenorders", query = "select p from Tankbeurt p where tankstation.id = :id and betaald = false "),
        @NamedQuery(name = "Tankbeurt.beurtbetalen", query = "update Tankbeurt set betaald = true where id = :id"),
        @NamedQuery(name = "Tankbeurt.getclosedorders", query = "select p from Tankbeurt p where betaald = true")
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


    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Userapp getTankstation() {
        return tankstation;
    }

    public void setTankstation(Userapp tankstation) {
        this.tankstation = tankstation;
    }

    @Id
    @GeneratedValue
    int id;

    @Min(0)
    @NotNull
    int liter;
    @ManyToOne
    Brandstof brandstof;
    @ManyToOne
    @NotNull
    Userapp user;

    @ManyToOne
    Coupon coupon;


    public boolean isBetaald() {
        return betaald;
    }

    public void setBetaald(boolean betaald) {
        this.betaald = betaald;
    }

    @ManyToOne
    Userapp tankstation;
    @Min(0)
    double prijs;


    @DefaultValue("false")
    boolean betaald;

    Timestamp created;

    public Tankbeurt() {

    }

    public Tankbeurt(Userapp user, Userapp tankstation, Brandstof brandstof, Coupon coupon, int liter) {
        this.user = user;
        this.coupon = coupon;
        this.liter = liter;
        this.brandstof = brandstof;
        this.tankstation = tankstation;
        calculatePrijs();
    }

    public Tankbeurt(Userapp user, Userapp tankstation, Brandstof brandstof, int liter) {
        this.user = user;
        this.liter = liter;
        this.brandstof = brandstof;
        this.tankstation = tankstation;
        calculatePrijs();
    }

    private void calculatePrijs() {
        Date date = new Date();
        created = new Timestamp(date.getTime());
        if (coupon == null) {
            prijs = liter * brandstof.prijs;
        } else {
            prijs = (liter * brandstof.prijs) * ((100 - coupon.percentOff) / 100);
        }
    }

}
