package fr.uge.friday;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import fr.uge.friday.entity.Task;
import fr.uge.friday.entity.User;
import fr.uge.friday.jsonparsing.JsonFunc;
import fr.uge.friday.repository.TaskRepository;
import fr.uge.friday.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;


@SpringBootApplication
public class FridayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FridayApplication.class, args);
    }

    /*
    @Bean
    public CommandLineRunner run(UserRepository userRepository, TaskRepository taskRepository) throws JsonProcessingException {

        return (args -> {

            String jsonSrc = "{ \"title\": \"It's Friday Again\" }";
            JsonNode node = JsonFunc.parse(jsonSrc);
            System.out.println(node.get("title").asText());

            userRepository.deleteAll();
            taskRepository.deleteAll();
            insertUsers(userRepository, "Xhavit", "123");
            insertUsers(userRepository, "Samy", "456");
            insertTasks(taskRepository, new Date(), "faire les courses", "Leclerc de Meaux");
            System.out.println(taskRepository.findAll());
            System.out.println(userRepository.findAll());
            System.out.println(userRepository.deleteUserByName("Xhavit"));
            System.out.println(userRepository.findAll());

        });
    }


     */
 
    private void insertUsers(UserRepository repository, String name, String password){
        repository.save(new User(name, password));
    }

    private void insertTasks(TaskRepository repository, Date date, String description, String location){
        repository.save(new Task(date, description, location));
    }
}
