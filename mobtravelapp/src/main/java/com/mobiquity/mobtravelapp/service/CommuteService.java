package com.mobiquity.mobtravelapp.service;

import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.mobiquity.mobtravelapp.exception.IncorrectFormatException;
import com.mobiquity.mobtravelapp.model.travel.RouteModel;
import com.mobiquity.mobtravelapp.model.travel.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommuteService {

    @Autowired
    TravelService travelService;

    @Autowired
    CalendarService calendarService;

    public List<Trip> getTripsForEvents(){
        List<Trip> trips = new ArrayList<>();

        List<Event> events = calendarService.getEvents();
        for(Event event : events){
            try {
                //String dateTime = formatStartTime(event.getStart()).toString();
                //System.out.println(dateTime);
                Trip trip = travelService.getTripFromNs(new RouteModel("Haarlem", event.getLocation(), "2019-10-23T17:00:00"));
                trips.add(trip);
            } catch (IncorrectFormatException e) {
                e.printStackTrace();
            }
        }

        return trips;
    }

    //TODO refactor to reformat EventDateTime to
    /*
    private Date formatStartTime(EventDateTime eventDateTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(sdf.format(eventDateTime));
    }
    */
}
