package fr.uge.friday.dto;

import net.fortuna.ical4j.model.Date;

public record EventSaveDTO(String description, String location, Date start, Date end, String username) {
}
