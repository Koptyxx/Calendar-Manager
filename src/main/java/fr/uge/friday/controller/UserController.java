package fr.uge.friday.controller;

import fr.uge.friday.converter.UserResponseConverter;
import fr.uge.friday.converter.UserSaveConverter;
import fr.uge.friday.dto.UserResponseDTO;
import fr.uge.friday.dto.UserSaveDTO;
import fr.uge.friday.entity.User;
import fr.uge.friday.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserResponseConverter userResponseConverter;
    private final UserSaveConverter userSaveConverter;

    private UserController(UserRepository userRepository, UserResponseConverter userResponseConverter, UserSaveConverter userSaveConverter) {
        this.userRepository = userRepository;
        this.userResponseConverter = userResponseConverter;
        this.userSaveConverter = userSaveConverter;
    }

    @GetMapping("/findAll")
    public List<UserResponseDTO> findAll(){
        List<User> userList = userRepository.findAll();
        return userResponseConverter.entityToDTO(userList);
    }

    @GetMapping("/find/{ID}")
    public UserResponseDTO findById(@PathVariable(value = "ID") UUID id){
        var user = userRepository.findById(id);
        return user.map(userResponseConverter::entityToDTO).orElse(null);
    }

    @GetMapping("/save")
    public UserSaveDTO save(@RequestBody UserSaveDTO userSaveDTO){
        User user = userSaveConverter.dtoToEntity(userSaveDTO);
        user = userRepository.save(user);
        return userSaveConverter.entityToDTO(user);
    }
}
