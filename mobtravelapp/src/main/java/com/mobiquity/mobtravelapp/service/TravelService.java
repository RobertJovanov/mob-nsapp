package com.mobiquity.mobtravelapp.service;

import com.mobiquity.mobtravelapp.model.RouteModel;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.print.attribute.standard.Media;
import java.util.Arrays;

@Service
public class TravelService {

    final String url = "https://gateway.apiportal.ns.nl/public-reisinformatie/api/v3/trips?";

    final String key = "7504c483d91f486a82b917743521ab40";

    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders httpHeaders = new HttpHeaders();

    public void getRoutes(RouteModel routeModel){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.set("Ocp-Apim-Subscription-Key", key);

        //TODO fix
        String parameters = routeModel.getFromStation() + "&" + routeModel.getToStation() + "&" + routeModel.getDateTime();

        HttpEntity<String> entity = new HttpEntity<String>(routeModel.toString(), httpHeaders);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println(result);
    }

}
