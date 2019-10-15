package com.mobiquity.mobtravelapp.service;


import com.mobiquity.mobtravelapp.model.WeatherModel.Weather;
import com.mobiquity.mobtravelapp.model.travelModel.Station;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

public class WeatherService {

    @Value("${darksky.net.api.url}")
    private String uri;

    final String key = System.getenv("DARKSKYAPIKEY");

    public Weather getWeather(Station station, String dateTime) {
        String url = MessageFormat.format(uri, key, station.getLatitude(), station.getLongitude(), dateTime);
        System.out.println(url);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Weather> responseEntity = restTemplate.getForEntity(url, Weather.class);
        Weather.Currently currently = responseEntity.getBody().getCurrently();
        System.out.println(currently.getSummary());
        return responseEntity.getBody();
    }

}
