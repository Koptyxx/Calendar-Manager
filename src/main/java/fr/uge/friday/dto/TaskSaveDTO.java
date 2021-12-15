package fr.uge.friday.dto;

import fr.uge.friday.entity.User;

import java.util.Date;

public record TaskSaveDTO(Date date, String desc, String loc, User user) {
}
