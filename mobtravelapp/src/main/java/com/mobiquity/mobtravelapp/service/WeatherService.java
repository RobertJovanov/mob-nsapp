package com.mobiquity.mobtravelapp.service;


import com.mobiquity.mobtravelapp.exception.WeatherException;
import com.mobiquity.mobtravelapp.model.WeatherModel.Weather;
import com.mobiquity.mobtravelapp.model.travelModel.Station;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

@Service
public class WeatherService {

    private String uri = "https://api.darksky.net/forecast/{0}/{1},{2},{3}";
    final String key = System.getenv("DARKSKYAPIKEY");

    /**
     * Extracts weather for Origin and Destination stations,
     * via a call to DarkSky API
     *
     * @param station  details latitude and longitude
     * @param dateTime current date and time
     * @return Weather object
     */
    public Weather getWeather(Station station, String dateTime) throws Exception{
        String url = MessageFormat.format(uri, key, station.getLatitude(), station.getLongitude(), dateTime);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Weather> responseEntity;
        try {
         responseEntity = restTemplate.getForEntity(url, Weather.class);
        } catch(RestClientException e){
            throw new WeatherException("Unable to fetch weather details");
        }

        return responseEntity.getBody();
    }

}
