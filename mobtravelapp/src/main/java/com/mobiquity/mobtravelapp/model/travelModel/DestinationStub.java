package com.mobiquity.mobtravelapp.model.travelModel;

import com.mobiquity.mobtravelapp.model.WeatherModel.Weather;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DestinationStub {
    private String plannedArrivalDateTime;
    private String actualArrivalDateTime;
    private String plannedArrivalTrack;
    private String actualArrivalTrack;
    private Station station;
    private Weather weather;
}
