package com.mobiquity.mobtravelapp.service;

//import com.fasterxml.jackson.annotation.JsonValue;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mobiquity.mobtravelapp.model.RouteModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;



@Service
public class TravelService {

    @Value("${ns.nl.api.url}")
    private String uri;

    final String key = "7504c483d91f486a82b917743521ab40";

    public void getRoutes(RouteModel routeModel)  {
        String url = MessageFormat.format(uri,"fromStation=" + routeModel.getFromStation(), "toStation=" + routeModel.getToStation(), "dateTime=" + routeModel.getDateTime());
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(url);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Ocp-Apim-Subscription-Key", key);

        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println(result.getBody());
        parseThroughJson(result.getBody());
    }

    private void parseThroughJson(String result) {
//        final JSONObject obj = new JSONObject();
        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        JsonArray trips = jsonObject.getAsJsonArray("trips");
        System.out.println(trips.size());
        for (int i = 0; i < trips.size(); i++) {
            JsonObject trip = trips.get(i).getAsJsonObject();
            System.out.println(trip.get("plannedDurationInMinutes"));
            JsonArray legs=trip.getAsJsonArray("legs");
            for(int j=0;j<legs.size();j++){
                JsonObject leg = legs.get(j).getAsJsonObject();
                System.out.println(leg.get("direction"));
            }




        }


    }


}
