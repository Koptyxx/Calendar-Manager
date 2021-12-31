package fr.uge.friday.entity;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.model.property.*;

import javax.persistence.*;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Calendar")
public class Event {

    @Id
    @GeneratedValue
    @Column(name = "id_Cal", nullable = false, unique = true)
    private UUID idCal;

    @Column(name = "calendar", nullable = false, length = 40000)
    private Calendar calendar;

    @ManyToOne
    @JoinTable(name = "UserNCal",
            joinColumns = @JoinColumn(name = "id_Cal"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    private static DateTime endDateTimeAllDay(String s) throws ParseException {
        var sb = new StringBuilder();
        for (int i = 0; i < s.length() - 6; i++) {
            sb.append(s.charAt(i));
        }
        sb.append("235959");
        return new DateTime(sb.toString());
    }

    public void addEvent(String description, String location, String start, boolean isAllDay) throws URISyntaxException, ParseException {
        Objects.requireNonNull(description);
        Objects.requireNonNull(location);
        Objects.requireNonNull(start);

        VEvent event;

        if (isAllDay)
            event = new VEvent(new DateTime(start), endDateTimeAllDay(start), description);
        else
            event = new VEvent(new DateTime(start), description);

        event.getProperties().add(new Location(location));
        event.getProperties().add(new Description(description));
        event.getProperties().add(new Organizer(user.getName()));

        calendar.getComponents().add(event);
    }

    public Event(User user){

        Objects.requireNonNull(user);

        calendar = new Calendar();
        calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);

        this.user = user;
    }

    public Event() {
    }

    public UUID getIdCal() {
        return idCal;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public User getUser() {
        return user;
    }

}
