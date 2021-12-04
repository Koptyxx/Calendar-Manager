package fr.uge.friday;

import fr.uge.friday.entity.Task;
import fr.uge.friday.entity.User;
import fr.uge.friday.repository.TaskRepository;
import fr.uge.friday.repository.UserRepository;

import java.util.Date;

public class Insertions {

    public static void insertUsers(UserRepository repository, String name, String password){
        repository.save(new User(name, password));
    }

    public static void insertTasks(UserRepository userRepository, TaskRepository repository, Date date, String description, String location, String username){

        var user = userRepository.findUserByUsernameEquals(username);
        repository.save(new Task(date, description, location, user));
    }

}
