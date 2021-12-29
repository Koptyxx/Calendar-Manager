package fr.uge.friday.dto;

public record EventSaveDTO(String description, String location, String start, String username, boolean isAllDay) {
}
