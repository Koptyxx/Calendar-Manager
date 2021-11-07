package fr.uge.friday;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CalendarManager {

    Calendar calendar;

    public CalendarManager() {
        calendar = new Calendar();
    }

    public void loadCalendarFromICSFile(String path) throws ParserException, IOException {
        FileInputStream fin = new FileInputStream(path);
        CalendarBuilder builder = new CalendarBuilder();
        calendar = builder.build(fin);
    }
    public static void main(String[] args) throws ParserException, IOException {
        String myCalendarString = "basic.ics";
        StringReader sin = new StringReader(myCalendarString);
        CalendarBuilder builder = new CalendarBuilder();
        Calendar calendar = builder.build(sin);
        System.out.println(calendar);
    }

}
