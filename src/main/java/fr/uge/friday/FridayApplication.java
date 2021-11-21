package fr.uge.friday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FridayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FridayApplication.class, args);
    }


    //marche mais parfois ça beug à cause des changements dans la bdd

    /*
    @Bean
    public CommandLineRunner run(UserRepository userRepository, TaskRepository taskRepository) throws JsonProcessingException {

        return (args -> {

            userRepository.deleteAll();
            taskRepository.deleteAll();

            insertUsers(userRepository, "Xhavit", "123");
            insertUsers(userRepository, "Samy", "456");
            insertTasks(userRepository, taskRepository, new Date(), "faire les courses", "Leclerc de Meaux", "Samy");
            insertTasks(userRepository, taskRepository, new Date(), "déjeuner avec Pat", "Paris", "Xhavit");
            insertTasks(userRepository, taskRepository, new Date(), "just tests", "Paris", "Xhavit");
            userRepository.findAll().forEach(System.out::println);

        });
    }

     */
}
