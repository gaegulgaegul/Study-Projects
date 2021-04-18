package me.whiteship.jpaspringboot;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Users")
public class User {

    @Id @GeneratedValue
    private Long id;

    private String username;

    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    private String yes;

    @Transient
    private String no;

    @Embedded
    @AttributeOverrides(
            @AttributeOverride(name = "street", column = @Column(name = "home_street"))
    )
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
