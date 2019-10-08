package com.mobiquity.mobtravelapp.service;

import com.mobiquity.mobtravelapp.model.RouteModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.Arrays;

@Service
public class TravelService {

    //final String url = "https://gateway.apiportal.ns.nl/public-reisinformatie/api/v3/trips?";
    @Value("${ns.nl.api.url}")
    private String uri;

    final String key = "7504c483d91f486a82b917743521ab40";



    public void getRoutes(RouteModel routeModel){
        String url=MessageFormat.format(uri,routeModel.getFromStation(),routeModel.getToStation(),routeModel.getDateTime());
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(url);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.set("Ocp-Api-Subscription-Key", key);


        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println(result);
    }

}
