package fr.uge.friday.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id", nullable = false)
    private Long task_id;
    @Column(name = "date", nullable = false)
    private Date date;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "location", nullable = false)
    private String location;

    @ManyToOne
    @JoinTable(name = "UserNTask",
            joinColumns = @JoinColumn(name = "task_id"),
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
                "id=" + task_id +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

}
