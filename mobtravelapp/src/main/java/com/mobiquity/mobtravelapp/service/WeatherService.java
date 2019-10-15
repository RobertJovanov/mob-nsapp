package com.mobiquity.mobtravelapp.service;


import com.mobiquity.mobtravelapp.model.WeatherModel.Weather;
import com.mobiquity.mobtravelapp.model.travelModel.Station;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
@Service
public class WeatherService {

    @Value("${api.darksky.net.url}")
    private String uri;


    final String key = System.getenv("DARKSKYAPIKEY");

    public Weather getWeather(Station station, String dateTime) {
        String url = MessageFormat.format(uri, key, station.getLatitude(), station.getLongitude(), dateTime);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Weather> responseEntity = restTemplate.getForEntity(url, Weather.class);
        return responseEntity.getBody();
    }

}
