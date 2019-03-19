package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
        @NamedQuery(name = "Userapp.findOne", query = "select p from Userapp p where p.id = :id"),
        @NamedQuery(name = "Userapp.getAll", query = "select p from Userapp p")
}
)
public class Userapp {

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String username;
    private String password;

    public Userapp() {
    }

    public Userapp(String username, String password) {
        this.username = username;
        this.password = password;
    }


}

