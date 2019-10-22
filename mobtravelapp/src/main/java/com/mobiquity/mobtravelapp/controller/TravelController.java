package com.mobiquity.mobtravelapp.controller;

import com.mobiquity.mobtravelapp.exception.IncorrectFormatException;
import com.mobiquity.mobtravelapp.model.travel.RouteModel;
import com.mobiquity.mobtravelapp.model.travel.Trip;
import com.mobiquity.mobtravelapp.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TravelController {

    @Autowired
    TravelService travelService;

    @RequestMapping(value = "/travelInfo/routes", method = RequestMethod.GET)
    public Trip getRoutes(@RequestParam(value = "fromStation") String fromStation, @RequestParam(value = "toStation") String toStation,
                          @RequestParam(value = "dateTime", defaultValue = "") String dateTime) throws IncorrectFormatException {

        return travelService.getTripFromNs(new RouteModel(fromStation,toStation,dateTime));
    }

    @RequestMapping("/")
    public String checkHealth(){
        return "Application is healthy.\n";
    }

}