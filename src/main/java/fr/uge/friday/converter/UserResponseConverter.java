package fr.uge.friday.converter;

import fr.uge.friday.dto.UserResponseDTO;
import fr.uge.friday.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserResponseConverter {

    public UserResponseDTO entityToDTO(User user){
        return new UserResponseDTO(user.getId(), user.getName());
    }

    public List<UserResponseDTO> entityToDTO(List<User> user){
        return user.stream().map(this::entityToDTO).collect(Collectors.toList());
    }
}
