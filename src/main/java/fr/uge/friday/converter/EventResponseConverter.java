package fr.uge.friday.converter;


import fr.uge.friday.dto.EventResponseDTO;
import fr.uge.friday.entity.Event;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventResponseConverter {

    public EventResponseDTO entityToDTO(Event cal){
        return new EventResponseDTO(cal.getIdCal(), cal.getCalendar());
    }

    public List<EventResponseDTO> entitiesToDTO(List<Event> calendars){
        return calendars.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

}
