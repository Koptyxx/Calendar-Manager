package fr.uge.friday.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id_user", nullable = false, unique = true)
    private UUID id_user;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;


    public User(String name, String password) {
        this.username = name;
        this.password = password;
    }

    public User() {
    }

    public String getName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UUID getId() {
        return id_user;
    }
}
