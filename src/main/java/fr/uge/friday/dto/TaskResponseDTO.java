package fr.uge.friday.dto;

import java.util.Date;
import java.util.UUID;

public record TaskResponseDTO(UUID id, Date date, String desc, String loc) {
}
