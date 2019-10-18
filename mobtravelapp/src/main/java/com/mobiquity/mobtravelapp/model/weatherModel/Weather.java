package com.mobiquity.mobtravelapp.model.weatherModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties
@Getter
public class Weather {
    private Currently currently;

    @JsonIgnoreProperties
    @Getter
    public class Currently {
        private String summary;
        private double temperature;
        private double apparentTemperature;
        private double humidity;
        private double windSpeed;
    }
}


