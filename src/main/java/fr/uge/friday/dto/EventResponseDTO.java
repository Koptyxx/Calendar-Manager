package fr.uge.friday.dto;

import net.fortuna.ical4j.model.Calendar;

import java.util.UUID;

public record EventResponseDTO(UUID id, Calendar calendar) {
}
