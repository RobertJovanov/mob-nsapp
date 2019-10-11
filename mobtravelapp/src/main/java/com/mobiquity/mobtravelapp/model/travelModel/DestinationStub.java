package com.mobiquity.mobtravelapp.model.travelModel;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class DestinationStub {
    private String plannedArrivalTime;
    private String actualArrivalTime;
    private String plannedTrack;
    private String actualTrack;
    private Station station;

}
