package fr.uge.friday.dto;

import java.util.Date;

public record TaskSaveDTO(Date date, String desc, String loc, String user) {
}
