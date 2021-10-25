package fr.uge.friday.dbManager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(UserRepository userRepository, TaskRepository taskRepository){
        return (args -> {
            insertUsers(userRepository, "Xhavit", "123");
            System.out.println(userRepository.findAll());
            insertUsers(userRepository, "Samy", "456");
            System.out.println(userRepository.findAllByNameEquals("Samy"));
            System.out.println(userRepository.findUsersByPasswordEquals(""));
            System.out.println(userRepository.findAll());
            insertTasks(taskRepository, new Date(), "faire les courses");
            System.out.println(taskRepository.findAll());
        });
    }

    private void insertUsers(UserRepository repository, String name, String password){
        repository.save(new User(name, password));
    }

    private void insertTasks(TaskRepository repository, Date date, String desc){
        repository.save(new Task(date, desc));
    }

}
