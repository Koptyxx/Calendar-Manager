package fr.uge.friday.controller;


import fr.uge.friday.converter.EventSaveConverter;
import fr.uge.friday.dto.EventSaveDTO;
import fr.uge.friday.entity.Event;
import fr.uge.friday.repository.EventRepository;
import fr.uge.friday.repository.UserRepository;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.data.UnfoldingReader;
import net.fortuna.ical4j.model.Calendar;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Objects;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventRepository eventRepository;
    private final EventSaveConverter eventSaveConverter;
    private final UserRepository userRepository;

    public EventController(EventRepository eventRepository, UserRepository userRepository, EventSaveConverter eventSaveConverter) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.eventSaveConverter = eventSaveConverter;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody EventSaveDTO eventSaveDTO) throws URISyntaxException, ParseException {
        Event event = eventSaveConverter.dtoToEntity(eventSaveDTO);
        eventRepository.save(event);
        URI location = URI.create(String.format("http://localhost:8080/user/find/%s", eventSaveDTO.username()));
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Void> deleteEventByUsername(@PathVariable String username){
        var user = userRepository.findUserByUsernameEquals(username);
        if (user.isPresent()) {
            eventRepository.deleteEventsByUser(user.get());
            eventRepository.save(new Event(user.get()));
        }
        return ResponseEntity.noContent().build();
    }

    private static void writeCal(Calendar cal, String file) throws IOException {
        FileOutputStream fout = new FileOutputStream(file);
        CalendarOutputter outputter = new CalendarOutputter();
        outputter.output(cal, fout);
    }

    @PostMapping("/write")
    public ResponseEntity<Void> writeCalInFile(@RequestBody String username) throws IOException {
        var user = userRepository.findUserByUsernameEquals(username);
        if(user.isPresent()){
            var event = eventRepository.findEventByUserEquals(user.get());
            writeCal(event.getCalendar(), username + "-Calendar.ics");
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    private static Calendar createCal(String file) throws IOException, ParserException {
        Objects.requireNonNull(file);
        CalendarBuilder builder = new CalendarBuilder();
        UnfoldingReader ufrdr = new UnfoldingReader(new FileReader(file));
        return builder.build(ufrdr);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createFromFile(@RequestBody String file, @RequestBody String username) throws IOException, ParserException {
        var user = userRepository.findUserByUsernameEquals(username);
        if(user.isPresent()){
            var event = eventRepository.findEventByUserEquals(user.get());
            event.setCalendar(createCal(file));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
