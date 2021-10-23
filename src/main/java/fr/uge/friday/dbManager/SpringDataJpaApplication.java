package fr.uge.friday.dbManager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(UserRepository repository){
        return (args -> {
            repository.deleteAll();
            insertUsers(repository, "Xhavit", "123");
            System.out.println(repository.findAll());
            insertUsers(repository, "Samy", "456");
            System.out.println(repository.findAllByNameEquals("Samy"));
            System.out.println(repository.findUsersByPasswordEquals(""));

        });
    }

    private void insertUsers(UserRepository repository, String name, String password){
        repository.save(new User(name, password));

    }

}
