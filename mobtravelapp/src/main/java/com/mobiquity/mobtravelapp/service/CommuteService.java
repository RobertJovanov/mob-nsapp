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

    public List<Trip> getTripsForEvents(){
        List<Trip> trips = new ArrayList<>();

        List<Event> events = calendarService.getEvents();
        
        try{
            if(events.size() == 1){
                Trip trip = travelService.getTripFromNs(
                        new RouteModel("Haarlem", events.get(0).getLocation(), formatStartTime(events.get(0).getStart()), "true"));
                trips.add(trip);
            }else if(events.size() > 1){
                Trip initialTrip = travelService.getTripFromNs(
                        new RouteModel("Haarlem", events.get(0).getLocation(), formatStartTime(events.get(0).getStart()), "true"));
                trips.add(initialTrip);
                for(int i = 1; i < events.size(); i++){
                    Trip trip = travelService.getTripFromNs(
                            new RouteModel(events.get(i - 1).getLocation(), events.get(i).getLocation(),
                                    formatStartTime(events.get(i).getStart()), "true"));
                    trips.add(trip);
                }
            }
        }catch (IncorrectFormatException e){
            e.printStackTrace();
        }
        return trips;
    }

    private String formatStartTime(EventDateTime eventDateTime){
        long unixDateTime = eventDateTime.getDateTime().getValue();
        Instant dateTime = Instant.ofEpochMilli(unixDateTime).atZone(ZoneId.of("Europe/Paris")).toInstant();
        return dateTime.toString();
    }
}
