package com.mobiquity.mobtravelapp.model.travelModel;

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

}

