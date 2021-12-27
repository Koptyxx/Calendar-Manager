package fr.uge.friday;

import fr.uge.friday.entity.Event;
import fr.uge.friday.entity.Task;
import fr.uge.friday.entity.User;
import fr.uge.friday.repository.EventRepository;
import fr.uge.friday.repository.TaskRepository;
import fr.uge.friday.repository.UserRepository;
import net.fortuna.ical4j.model.Date;

import java.net.URISyntaxException;

public class Insertions {

    public static void insertUsers(UserRepository repository, String name, String password){
        repository.save(new User(name, password));
    }

    public static void insertTasks(UserRepository userRepository, TaskRepository repository, Date date, String description, String location, String username){
        var user = userRepository.findUserByUsernameEquals(username);
        user.ifPresent(value -> repository.save(new Task(date, description, location, value)));
    }

    public static void insertEvent(UserRepository userRepository, EventRepository eventRepository, String description, String location, Date start, String username) throws URISyntaxException {
        var user = userRepository.findUserByUsernameEquals(username);
        if (user.isPresent())
            eventRepository.save(new Event(description, location, start, user.get()));
    }

}
