package fr.uge.friday;

import fr.uge.friday.repository.EventRepository;
import fr.uge.friday.repository.TaskRepository;
import fr.uge.friday.repository.UserRepository;
import net.fortuna.ical4j.model.Date;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static fr.uge.friday.Insertions.*;


@SpringBootApplication
public class FridayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FridayApplication.class, args);
    }
    @Bean
    public CommandLineRunner run(UserRepository userRepository, TaskRepository taskRepository, EventRepository eventRepository) {
        return (args -> {
            taskRepository.deleteAll();
            eventRepository.deleteAll();
            userRepository.deleteAll();
            insertUsers(userRepository, "Xhavit", "123");
            insertUsers(userRepository, "Samy", "456");
            insertTasks(userRepository, taskRepository, new Date(), "faire les courses", "Leclerc de Meaux", "Samy");
            insertTasks(userRepository, taskRepository, new Date(), "déjeuner avec Pat", "Paris", "Xhavit");
            insertTasks(userRepository, taskRepository, new Date(), "just tests", "Paris", "Xhavit");
            insertEvent(userRepository, eventRepository, "faire les courses", "Carrefour", "20211229T200000", "Xhavit", false);

        });
    }
}
