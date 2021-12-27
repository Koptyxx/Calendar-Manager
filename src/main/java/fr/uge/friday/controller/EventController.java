package fr.uge.friday.controller;


import fr.uge.friday.converter.EventResponseConverter;
import fr.uge.friday.converter.EventSaveConverter;
import fr.uge.friday.dto.EventResponseDTO;
import fr.uge.friday.dto.EventSaveDTO;
import fr.uge.friday.entity.Event;
import fr.uge.friday.repository.EventRepository;
import fr.uge.friday.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventRepository eventRepository;
    private final EventResponseConverter eventResponseConverter;
    private final EventSaveConverter eventSaveConverter;
    private final UserRepository userRepository;

    public EventController(EventRepository eventRepository, EventResponseConverter eventResponseConverter, UserRepository userRepository, EventSaveConverter eventSaveConverter) {
        this.eventRepository = eventRepository;
        this.eventResponseConverter = eventResponseConverter;
        this.userRepository = userRepository;
        this.eventSaveConverter = eventSaveConverter;
    }

    @GetMapping("/findAll")
    public List<EventResponseDTO> findAll(){
        List<Event> events = eventRepository.findAll();
        return eventResponseConverter.entitiesToDTO(events);
    }

    @GetMapping("/find/username/{username}")
    public List<EventResponseDTO> findByDate(@PathVariable String username){
        var user = userRepository.findUserByUsernameEquals(username);
        return eventResponseConverter.entitiesToDTO(Objects.requireNonNull(user.map(eventRepository::findByUserEquals).orElse(null)));
    }

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody EventSaveDTO eventSaveDTO) throws URISyntaxException {
        Event event = eventSaveConverter.dtoToEntity(eventSaveDTO);
        eventRepository.save(event);
        URI location = URI.create(String.format("http://localhost:8080/user/find/%s", eventSaveDTO.username()));
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEventById(@PathVariable UUID id){
        eventRepository.deleteEventByIdCal(id);
        return ResponseEntity.noContent().build();
    }
}
