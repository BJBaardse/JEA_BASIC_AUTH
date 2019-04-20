package models;

import jwt.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
        @NamedQuery(name = "Userapp.findOne", query = "select p from Userapp p where p.id = :id"),
        @NamedQuery(name = "Userapp.findStations", query = "select p from Userapp p where p.role = 1"),
        @NamedQuery(name = "Userapp.getAll", query = "select p from Userapp p"),
        @NamedQuery(name = "Userapp.checkcreds", query = "select p from Userapp p where p.username = :username and p.password = :password")

}
)
public class Userapp {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private String username;
    private String password;
    
    private Role role;

    public Userapp() {
    }

    public Userapp(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }


}

