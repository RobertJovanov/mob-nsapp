package com.mobiquity.mobtravelapp.model.travel;
import com.mobiquity.mobtravelapp.model.weather.Weather;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OriginStub {
    private final String plannedDepartureDateTime;
    private final String actualDepartureDateTime;
    private final String plannedArrivalTrack;
    private final String actualArrivalTrack;
    private final Station station;
    private final Weather weather;
}

