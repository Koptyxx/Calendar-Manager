package com.uge.friday.springdatajpa;

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
    public CommandLineRunner run(UsersRepository repository){
        return (args -> {
            insertUsers(repository, "Remi", "Forax");
            System.out.println(repository.findAll());
            //System.out.println(repository.findAllByFirstNameContaining("j"));
            insertUsers(repository, "Samy", "Ghamri");
            System.out.println(repository.findAll());
        });
    }

    private void insertUsers(UsersRepository repository, String firstName, String lastName){
        repository.save(new Users(firstName, lastName));
    }

}
