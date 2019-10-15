package com.mobiquity.mobtravelapp.model.travelModel;
import com.mobiquity.mobtravelapp.model.WeatherModel.Weather;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OriginStub {
    private String plannedDepartureDateTime;
    private String actualDepartureDateTime;
    private String plannedArrivalTrack;
    private String actualArrivalTrack;
    private Station station;
    private Weather weather;

}

