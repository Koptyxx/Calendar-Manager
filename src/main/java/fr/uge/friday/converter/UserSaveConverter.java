package fr.uge.friday.converter;


import fr.uge.friday.dto.UserSaveDTO;
import fr.uge.friday.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserSaveConverter {

    public UserSaveDTO entityToDTO(User user){
        return new UserSaveDTO(user.getName(), user.getPassword());
    }

    public User dtoToEntity(UserSaveDTO userSaveDTO){
        return new User(userSaveDTO.username(), userSaveDTO.password());
    }
}
