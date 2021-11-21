package fr.uge.friday;


import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.data.UnfoldingReader;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.MapTimeZoneCache;

import java.io.FileReader;
import java.io.IOException;


public class CalendarManager {

    private Calendar calendar;

    public CalendarManager() {
        calendar = new Calendar();
        calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);
    }

    public void loadCalendarFromFile(String file) throws IOException, ParserException {
        CalendarBuilder builder = new CalendarBuilder();
        UnfoldingReader ufrdr = new UnfoldingReader(new FileReader(file));
        calendar = builder.build(ufrdr);
    }

    public void createEvent() {

    }

    public void showCalendar(){
        System.out.println(calendar);
    }

    public static void main(String[] args) throws ParserException, IOException {
        var calendar = new CalendarManager();
        System.setProperty("net.fortuna.ical4j.timezone.cache.impl", MapTimeZoneCache.class.getName());
        calendar.loadCalendarFromFile("example.ics");
        calendar.showCalendar();
    }

}
