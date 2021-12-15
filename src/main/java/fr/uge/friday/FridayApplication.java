package fr.uge.friday;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.uge.friday.repository.TaskRepository;
import fr.uge.friday.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;

import static fr.uge.friday.Insertions.insertTasks;
import static fr.uge.friday.Insertions.insertUsers;


@SpringBootApplication
public class FridayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FridayApplication.class, args);
    }
    @Bean
    public CommandLineRunner run(UserRepository userRepository, TaskRepository taskRepository) throws JsonProcessingException {
        return (args -> {
            taskRepository.deleteAll();
            userRepository.deleteAll();
            insertUsers(userRepository, "Xhavit", "123");
            insertUsers(userRepository, "Samy", "456");
            insertTasks(userRepository, taskRepository, new Date(), "faire les courses", "Leclerc de Meaux", "Samy");
            insertTasks(userRepository, taskRepository, new Date(), "déjeuner avec Pat", "Paris", "Xhavit");
            insertTasks(userRepository, taskRepository, new Date(), "just tests", "Paris", "Xhavit");
        });
    }
}
