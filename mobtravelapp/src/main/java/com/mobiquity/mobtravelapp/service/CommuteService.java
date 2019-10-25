package com.mobiquity.mobtravelapp.service;

import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.mobiquity.mobtravelapp.exception.IncorrectFormatException;
import com.mobiquity.mobtravelapp.model.travel.RouteModel;
import com.mobiquity.mobtravelapp.model.travel.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommuteService {

    @Autowired
    TravelService travelService;

    @Autowired
    CalendarService calendarService;

    /**
     * Gets the events from google calendar and creates the necessary trips for the day, using the specified home location
     * as a starting point for the Trips and as a final destination for the trip after the last event.
     * @param home a string representing the station a person uses for their commute
     * @return a list of all the necessary trips for ones daily commute
     */
    public List<Trip> getCommute(String home){
        List<Trip> trips = new ArrayList<>();

        List<Event> events = calendarService.getEvents();

        try{
            if(events.size() > 0){
                trips.add(createInitialTrip(events, home));
                if(events.size() > 1){
                    for(int i = 1; i < events.size(); i++){
                        Trip trip = travelService.getTripFromNs(
                                new RouteModel(events.get(i - 1).getLocation(), events.get(i).getLocation(),
                                        formatStartTime(events.get(i).getStart()), "true"));
                        trips.add(trip);
                    }
                }
                trips.add(createReturnTrip(events, home));
            }
        }catch (IncorrectFormatException e){
            e.printStackTrace();
        }
        return trips;
    }

    private Trip createInitialTrip(List<Event> events, String home) throws IncorrectFormatException{
        return travelService.getTripFromNs(
                new RouteModel(home, events.get(0).getLocation(), formatStartTime(events.get(0).getStart()), "true"));
    }

    private Trip createReturnTrip(List<Event> events, String home) throws IncorrectFormatException{
        return travelService.getTripFromNs(
                new RouteModel(events.get(events.size() - 1).getLocation(), home, formatStartTime(events.get(events.size() - 1).getEnd())));
    }

    private String formatStartTime(EventDateTime eventDateTime){
        long unixDateTime = eventDateTime.getDateTime().getValue();
        Instant dateTime = Instant.ofEpochMilli(unixDateTime).atZone(ZoneId.of("Europe/Paris")).toInstant();
        return dateTime.toString();
    }
}
