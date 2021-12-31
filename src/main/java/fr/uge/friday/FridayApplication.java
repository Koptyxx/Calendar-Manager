package fr.uge.friday;

import fr.uge.friday.repository.EventRepository;
import fr.uge.friday.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static fr.uge.friday.Insertions.insertEvent;
import static fr.uge.friday.Insertions.insertUsers;


@SpringBootApplication
public class FridayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FridayApplication.class, args);
    }
    @Bean
    public CommandLineRunner run(UserRepository userRepository, EventRepository eventRepository) {
        return (args -> {
            eventRepository.deleteAll();
            userRepository.deleteAll();
            insertUsers(userRepository, "Xhavit", "123");
            insertUsers(userRepository, "Samy", "456");
            insertEvent(userRepository, eventRepository, "faire les courses", "Carrefour", "20211229T200000", "Xhavit", false);

        });
    }
}
