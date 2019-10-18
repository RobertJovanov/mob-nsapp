package com.mobiquity.mobtravelapp.model.travelModel;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StopStub {
    private final String plannedArrivalDateTime;
    private final String actualArrivalDateTime;
    private final String plannedDepartureDateTime;
    private final String actualDepartureDateTime;
    private final String plannedArrivalTrack;
    private final String actualArrivalTrack;
    private final Station station;



}
