package fr.uge.friday;

import fr.uge.friday.repository.EventRepository;
import fr.uge.friday.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


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
        });
    }
}
