package com.mobiquity.mobtravelapp.controller;

import com.google.api.services.calendar.model.Event;
import com.mobiquity.mobtravelapp.exception.IncorrectFormatException;
import com.mobiquity.mobtravelapp.model.travel.RouteModel;
import com.mobiquity.mobtravelapp.model.travel.Trip;
import com.mobiquity.mobtravelapp.service.CalendarService;
import com.mobiquity.mobtravelapp.service.CommuteService;
import com.mobiquity.mobtravelapp.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TravelController {

    @Autowired
    TravelService travelService;

    @Autowired
    CalendarService calendarService;

    @Autowired
    CommuteService commuteService;

    @RequestMapping(value = "/travelInfo/routes", method = RequestMethod.GET)
    public Trip getRoutes(@RequestParam(value = "fromStation") String fromStation, @RequestParam(value = "toStation") String toStation,
                          @RequestParam(value = "dateTime", defaultValue = "") String dateTime,
                          @RequestParam(value = "searchForArrival", defaultValue = "false") String searchForArrival)
            throws IncorrectFormatException {

        return travelService.getTripFromNs(new RouteModel(fromStation,toStation,dateTime, searchForArrival));
    }

    @RequestMapping("/")
    public String checkHealth(){
        return "Application is healthy.\n";
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public List<Event> getEventList(){
        return calendarService.getEvents();
    }

    @RequestMapping(value = "/events/commute")
    public List<Trip> getTripsForEvents(){
        return commuteService.getTripsForEvents();
    }

}