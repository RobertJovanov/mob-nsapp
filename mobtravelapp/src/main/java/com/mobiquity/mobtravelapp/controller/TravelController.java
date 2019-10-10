package com.mobiquity.mobtravelapp.controller;

import com.mobiquity.mobtravelapp.model.RouteModel;
import com.mobiquity.mobtravelapp.model.Trip;
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
    public Trip getRoutes(@RequestParam(value ="fromStation") String fromStation, @RequestParam(value = "toStation") String toStation, @RequestParam(value = "dateTime") String dateTime) throws IOException {
         return travelService.getRoutes(new RouteModel(fromStation, toStation, dateTime));
    }


}