package fr.uge.friday;

import fr.uge.friday.entity.Event;
import fr.uge.friday.entity.User;
import fr.uge.friday.repository.EventRepository;
import fr.uge.friday.repository.UserRepository;

import java.net.URISyntaxException;
import java.text.ParseException;

public class Insertions {

    public static void insertUsers(UserRepository repository, String name, String password){
        repository.save(new User(name, password));
    }

    public static void insertEvent(UserRepository userRepository, EventRepository eventRepository, String description, String location, String start, String username, boolean isAllDay) throws URISyntaxException, ParseException {
        var user = userRepository.findUserByUsernameEquals(username);
        Event event;
        if(user.isPresent()) {
            event = new Event(user.get());
            event.addEvent(description, location, start, isAllDay);
            eventRepository.save(event);
        }
    }

}
