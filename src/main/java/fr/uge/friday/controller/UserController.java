package fr.uge.friday.controller;

import fr.uge.friday.converter.UserResponseConverter;
import fr.uge.friday.converter.UserSaveConverter;
import fr.uge.friday.dto.UserResponseDTO;
import fr.uge.friday.dto.UserSaveDTO;
import fr.uge.friday.entity.User;
import fr.uge.friday.repository.EventRepository;
import fr.uge.friday.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserResponseConverter userResponseConverter;
    private final UserSaveConverter userSaveConverter;
    private final EventRepository eventRepository;

    private UserController(UserRepository userRepository, EventRepository eventRepository, UserResponseConverter userResponseConverter, UserSaveConverter userSaveConverter) {
        this.userRepository = userRepository;
        this.userResponseConverter = userResponseConverter;
        this.userSaveConverter = userSaveConverter;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/findAll")
    public List<UserResponseDTO> findAll(){
        List<User> userList = userRepository.findAll();
        return userResponseConverter.entityToDTO(userList);
    }

    @GetMapping("/find/{username}")
    public UserResponseDTO findByUsername(@PathVariable String username){
        var user = userRepository.findUserByUsernameEquals(username);
        return user.map(userResponseConverter::entityToDTO).orElse(null);
    }

    @PostMapping("/save")
    public UserSaveDTO save(@RequestBody UserSaveDTO userSaveDTO){
        User user = userSaveConverter.dtoToEntity(userSaveDTO);
        user = userRepository.save(user);
        return userSaveConverter.entityToDTO(user);
    }

    @PostMapping("/check")
    public ResponseEntity<Void> check(@RequestBody UserSaveDTO userSaveDTO){
        var user = userRepository.findUserByUsernameAndPasswordEquals(userSaveDTO.username(), userSaveDTO.password());
        if(user.isPresent())
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/check/username/{username}")
    public ResponseEntity<Void> checkUsername(@PathVariable String username){
        var user = userRepository.findUserByUsernameEquals(username);
        if(user.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Void> deleteUserByUsername(@PathVariable String username){

        var user = userRepository.findUserByUsernameEquals(username);
        user.ifPresent(eventRepository::deleteEventsByUser);
        userRepository.deleteUserByUsername(username);
        return ResponseEntity.noContent().build();
    }
}
