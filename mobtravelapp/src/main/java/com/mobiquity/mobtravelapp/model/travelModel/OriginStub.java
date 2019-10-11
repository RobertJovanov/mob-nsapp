package com.mobiquity.mobtravelapp.model.travelModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OriginStub {
    private String plannedDepartureTime;
    private String actualDepartureTime;
    private String plannedTrack;
    private String actualTrack;
    private Station station;

}
