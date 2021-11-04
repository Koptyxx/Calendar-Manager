package fr.uge.friday.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "date", nullable = false)
    private Date date;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "location", nullable = false)
    private String location;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public Task(Date date, String description, String location) {
        this.date = date;
        this.description = description;
        this.location = location;
    }

    public Task(){
    }

}
