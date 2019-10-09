package com.mobiquity.mobtravelapp.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mobiquity.mobtravelapp.model.RouteModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.stream.IntStream;


@Service
public class TravelService {

   private final Logger logger = LoggerFactory.getLogger(TravelService.class);

    @Value("${ns.nl.api.url}")
    private String uri;

    final String key = "7504c483d91f486a82b917743521ab40";


    public void getRoutes(RouteModel routeModel)  {
        String url = MessageFormat.format(uri,"fromStation=" + routeModel.getFromStation(), "toStation=" + routeModel.getToStation(), "dateTime=" + routeModel.getDateTime());
        RestTemplate restTemplate = new RestTemplate();
       // System.out.println(url);
        logger.info(url);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Ocp-Apim-Subscription-Key", key);

        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println(result.getBody());
        parseJson(result.getBody());
    }

    /**
     *
     * @param result
     */
    /*
    private void parseJson(String result) {
        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        JsonArray trips = jsonObject.getAsJsonArray("trips");
        System.out.println(trips.size());
        IntStream.range(0, trips.size()).mapToObj(i -> trips.get(i).getAsJsonObject()).forEach(trip -> {
            System.out.println(trip.get("plannedDurationInMinutes"));
            JsonArray legs = trip.getAsJsonArray("legs");
            IntStream.range(0, legs.size()).mapToObj(j -> legs.get(j).getAsJsonObject()).map(leg -> leg.get("direction")).forEach(System.out::println);
        });
        */

    public JsonArray parseJson(String result) {
        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        JsonArray trips = jsonObject.getAsJsonArray("trips");
        return trips;
    };

}
