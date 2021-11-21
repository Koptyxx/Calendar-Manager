package fr.uge.friday.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/messages")
public class FridayController {
    @GetMapping("/hello")
    public String forecast(){
        return "Hello it's Friday again!";
    }
}
