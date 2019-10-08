package com.mobiquity.mobtravelapp.controller;

import com.mobiquity.mobtravelapp.model.RouteModel;
import com.mobiquity.mobtravelapp.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/travelInfo")
public class TravelController {

    @Autowired
    TravelService travelService;


    @GetMapping("/routes/{fromStation}/{toStation}/{dateTime}")
    public void getRoutes(@PathVariable("fromStation") String fromStation, @PathVariable("toStation") String toStation,@PathVariable("dateTime") String dateTime ){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        travelService.getRoutes( new RouteModel(fromStation,toStation,dateTime));

    }

}
