package com.mobiquity.mobtravelapp.controller;

import com.mobiquity.mobtravelapp.model.travelModel.RouteModel;
import com.mobiquity.mobtravelapp.model.travelModel.Trip;
import com.mobiquity.mobtravelapp.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/travelInfo")
public class TravelController {

    @Autowired
    TravelService travelService;

    //@GetMapping("/routes")
    @RequestMapping("/travelInfo/routes")
    public Trip getRoutes(@RequestParam(value = "fromStation") String fromStation, @RequestParam(value = "toStation") String toStation,
                          @RequestParam(value = "dateTime", defaultValue = "") String dateTime) throws Exception {
        return travelService.getTripFromNs(RouteModel.builder().fromStation(fromStation).toStation(toStation).dateTime(dateTime).build());
    }

    @RequestMapping("/")
    public String checkHealth(){
        return "We are fine and dandy\n";
    }


}