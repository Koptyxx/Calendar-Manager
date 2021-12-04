package fr.uge.friday.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    public String forecast(){
        return "Hello it's Friday again!";
    }
}

