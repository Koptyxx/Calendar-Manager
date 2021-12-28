package fr.uge.friday.dto;

import net.fortuna.ical4j.model.DateTime;

public record EventSaveDTO(String description, String location, DateTime start, String username, boolean isAllDay) {
}
