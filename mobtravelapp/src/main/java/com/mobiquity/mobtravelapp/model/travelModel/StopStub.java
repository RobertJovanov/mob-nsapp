package com.mobiquity.mobtravelapp.model.travelModel;



import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StopStub {
    private String plannedArrivalDateTime;
    private String actualArrivalDateTime;
    private String plannedDepartureDateTime;
    private String actualDepartureDateTime;
    private String plannedArrivalTrack;
    private String actualArrivalTrack;
    private Station station;



}
