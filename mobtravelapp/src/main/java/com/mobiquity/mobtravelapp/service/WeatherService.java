package com.mobiquity.mobtravelapp.service;

import com.mobiquity.mobtravelapp.model.travelModel.Station;
import org.springframework.beans.factory.annotation.Value;

import java.text.MessageFormat;

public class WeatherService {

    @Value("{darksky.net.api.url}")
    private String uri;

    final String key = System.getenv("DARKSKYAPIKEY");


}
