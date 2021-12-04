package fr.uge.friday.converter;


import fr.uge.friday.dto.UserSaveDTO;
import fr.uge.friday.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserSaveConverter {

    public UserSaveDTO entityToDTO(User user){
        return new UserSaveDTO(user.getName(), user.getPassword());
    }

    public User dtoToEntity(UserSaveDTO userSaveDTO){
        return new User(userSaveDTO.username(), userSaveDTO.password());
    }

    public List<UserSaveDTO> entityToDTO(List<User> user){
        return user.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public List<User> dtoToEntity(List<UserSaveDTO> userSaveDTO){
        return userSaveDTO.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
