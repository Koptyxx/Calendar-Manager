package fr.uge.friday;

import fr.uge.friday.entity.Task;
import fr.uge.friday.entity.User;
import fr.uge.friday.repository.TaskRepository;
import fr.uge.friday.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }
    /*@Bean
    public CommandLineRunner run(UserRepository userRepository, TaskRepository taskRepository){
        return (args -> {
            userRepository.deleteAll();
            taskRepository.deleteAll();
            insertUsers(userRepository, "Xhavit", "123");
            insertUsers(userRepository, "Samy", "456");
            System.out.println(userRepository.findAllByNameEquals("Samy"));
            insertTasks(taskRepository, new Date(), "faire les courses", "Leclerc de Meaux");
            System.out.println(taskRepository.findAll());
            System.out.println(userRepository.findAll());
        });
    }
 
    private void insertUsers(UserRepository repository, String name, String password){
        repository.save(new User(name, password));
    }

    private void insertTasks(TaskRepository repository, Date date, String description, String location){
        repository.save(new Task(date, description, location));
    }*/

    /*@PostMapping("/form")
    public String formSubmitted(@RequestParam("form") String f) {
        return String.format("Text %s uploaded successfully", f);
    }*/

}
