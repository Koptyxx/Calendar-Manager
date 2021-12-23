package fr.uge.friday.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Tasks")
public class Task {
    @Id
    @GeneratedValue
    @Column(name = "id_Task", nullable = false)
    private UUID idTask;

    @Column(name = "date", nullable = false)
    private Date date;

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "location", nullable = false)
    private String location;

    @ManyToOne
    @JoinTable(name = "UserNTask",
            joinColumns = @JoinColumn(name = "id_Task"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    public Task(Date date, String description, String location, User user) {
        this.date = date;
        this.description = description;
        this.location = location;
        this.user = user;
    }

    public Task(){
    }


    @Override
    public String toString() {
        return "Task{" +
                "id=" + idTask +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public User getUser() {
        return user;
    }

    public UUID getId() {
        return idTask;
    }
}
