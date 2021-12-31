package fr.uge.friday.converter;

import fr.uge.friday.dto.EventSaveDTO;
import fr.uge.friday.entity.Event;
import fr.uge.friday.repository.EventRepository;
import fr.uge.friday.repository.UserRepository;
import org.springframework.stereotype.Component;
import java.net.URISyntaxException;
import java.text.ParseException;

@Component
public class EventSaveConverter {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    private EventSaveConverter(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }


    public Event dtoToEntity(EventSaveDTO event) throws URISyntaxException, ParseException {
        var user = userRepository.findUserByUsernameEquals(event.username());
        Event cal = null;
        if(user.isPresent()) {
            var calendar = eventRepository.findEventByUserEquals(user.get());
            cal = calendar.orElseGet(() -> new Event(user.get()));
            cal.addEvent(event.description(), event.location(), event.start(), event.isAllDay());
        }
        return cal;
    }
}
