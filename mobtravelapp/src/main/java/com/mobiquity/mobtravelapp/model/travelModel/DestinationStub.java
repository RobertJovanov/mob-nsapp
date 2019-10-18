package com.mobiquity.mobtravelapp.model.travelModel;

import com.mobiquity.mobtravelapp.model.weatherModel.Weather;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DestinationStub {
    private final String plannedArrivalDateTime;
    private final String actualArrivalDateTime;
    private final String plannedArrivalTrack;
    private final String actualArrivalTrack;
    private final Station station;
    private final Weather weather;
}
