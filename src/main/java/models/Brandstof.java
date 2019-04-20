package models;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@Entity
@NamedQueries({
        @NamedQuery(name = "Brandstof.findone", query = "select p from Brandstof p where p.id = :id"),
        @NamedQuery(name = "Brandstof.getall", query = "select p from Brandstof p")
}
)
public class Brandstof {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
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
    @NotNull
    String naam;
    double prijs;
    public Brandstof(){

    }
    public Brandstof(String naam, double prijs){
        this.naam = naam;
        this.prijs = prijs;
    }


}
