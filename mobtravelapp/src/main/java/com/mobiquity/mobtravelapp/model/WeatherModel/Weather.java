package com.mobiquity.mobtravelapp.model.WeatherModel;

import lombok.*;


@Builder
@Getter
public class Weather {
    private String summary;
    private double temperature;
    private double apparentTemperature;
    private double humidity;
    private double speed;
}
