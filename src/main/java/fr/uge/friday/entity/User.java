package fr.uge.friday.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user", nullable = false)
    private Long id_user;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "password", nullable = false)
    private String password;


    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "Users{" +
                "id_user=" + id_user +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
