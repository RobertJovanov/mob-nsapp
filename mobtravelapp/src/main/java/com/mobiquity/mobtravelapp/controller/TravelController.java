package com.mobiquity.mobtravelapp.controller;

import com.mobiquity.mobtravelapp.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/travelInfo")
public class TravelController {

    @Autowired
    TravelService travelService;

    @GetMapping("/routes")
    public void getRoutes(){
        travelService.getRoutes();
    }

}
