package com.mobiquity.mobtravelapp.controller;

import com.mobiquity.mobtravelapp.model.travelModel.Leg;
import com.mobiquity.mobtravelapp.model.travelModel.RouteModel;
import com.mobiquity.mobtravelapp.model.travelModel.Trip;
import com.mobiquity.mobtravelapp.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/travelInfo")
public class TravelController {

    @Autowired
    TravelService travelService;

    @GetMapping("/routes")
    public Trip getRoutes(@RequestParam(value = "fromStation") String fromStation, @RequestParam(value = "toStation") String toStation,
                          @RequestParam(value = "dateTime", defaultValue = "") String dateTime, @RequestParam(defaultValue = "0") int routeLimit) throws IOException {
        return travelService.getRoutes(RouteModel.builder().fromStation(fromStation).toStation(toStation).dateTime(dateTime).routeLimit(routeLimit).build());
    }


}