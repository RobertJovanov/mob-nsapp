package com.mobiquity.mobtravelapp.model.travelModel;



import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StopStub {
    private String plannedArrivalTime;
    private String actualArrivalTime;
    private String plannedDepartureTime;
    private String actualDepartureTime;
    private String plannedTrack;
    private String actualTrack;
    private Station station;



}
