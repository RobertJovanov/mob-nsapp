package com.mobiquity.mobtravelapp.controller;

import com.mobiquity.mobtravelapp.exception.IncorrectFormatException;
import com.mobiquity.mobtravelapp.model.travel.RouteModel;
import com.mobiquity.mobtravelapp.model.travel.Trip;
import com.mobiquity.mobtravelapp.service.CalendarService;
import com.mobiquity.mobtravelapp.service.CommuteService;
import com.mobiquity.mobtravelapp.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TravelController {

    @Autowired
    private TravelService travelService;

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private CommuteService commuteService;

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

    @RequestMapping(value = "/events/commute")
    public List<Trip> getTripsForEvents(@RequestParam(value = "home") String home){
        return commuteService.getCommute(home);
    }

}