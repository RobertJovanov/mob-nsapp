package com.mobiquity.mobtravelapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Station {
    private String name;
    private String plannedDepartureTime;
    private String actualDepartureTime;
    private String plannedArrivalTime;
    private String actualArrivalTime;
    private String plannedTrack;
    private String actualTrack;


}
