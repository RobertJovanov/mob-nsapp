package com.mobiquity.mobtravelapp.model.travelModel;

import lombok.*;

import java.util.List;

@Getter
@Builder
public class Route {
    private int index;
    private int plannedDurationInMinutes;
    private int transfers;
    private String status;
    private List<Leg> legs;

}
