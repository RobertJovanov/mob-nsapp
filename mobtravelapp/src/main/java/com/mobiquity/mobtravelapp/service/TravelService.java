package com.mobiquity.mobtravelapp.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.print.attribute.standard.Media;
import java.util.Arrays;

@Service
public class TravelService {

    final String url = "https://gateway.apiportal.ns.nl/public-reisinformatie/api/v3/trips?dateTime=2019-10-07T14%3A45%3A59.431Z&originTransit=False&originWalk=False&originBike=False&originCar=False&travelAssistanceTransferTime=0&searchForAccessibleTrip=False&destinationTransit=False&destinationWalk=False&destinationBike=False&destinationCar=False&excludeHighSpeedTrains=False&excludeReservationRequired=False&passing=False&fromStation=Amsterdam Zuid&toStation=Haarlem";

    final String key = "7504c483d91f486a82b917743521ab40";

    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders httpHeaders = new HttpHeaders();

    public void getRoutes(){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.set("Ocp-Apim-Subscription-Key", key);

        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println(result);
    }

}
