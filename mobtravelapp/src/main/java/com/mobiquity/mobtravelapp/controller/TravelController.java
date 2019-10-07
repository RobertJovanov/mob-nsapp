package com.mobiquity.mobtravelapp.controller;

import com.mobiquity.mobtravelapp.model.RouteModel;
import com.mobiquity.mobtravelapp.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/travelInfo")
public class TravelController {

    @Autowired
    TravelService travelService;

    @GetMapping("/routes")
    public void getRoutes(@RequestBody RouteModel routeModel){
        travelService.getRoutes(routeModel);
    }

}
