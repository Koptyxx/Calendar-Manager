package fr.uge.friday.converter;

import fr.uge.friday.dto.EventSaveDTO;
import fr.uge.friday.entity.Event;
import fr.uge.friday.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;

@Component
public class EventSaveConverter {

    private final UserRepository userRepository;

    private EventSaveConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Event dtoToEntity(EventSaveDTO event) throws URISyntaxException {
        var user = userRepository.findUserByUsernameEquals(event.username());
        if(user.isPresent())
            return new Event(event.description(), event.location(), event.start(), user.get());
        return null;
    }
}
