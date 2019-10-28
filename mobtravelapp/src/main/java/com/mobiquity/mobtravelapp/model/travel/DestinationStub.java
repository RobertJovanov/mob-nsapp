package com.mobiquity.mobtravelapp.model.travel;

import com.mobiquity.mobtravelapp.model.weather.Weather;
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
