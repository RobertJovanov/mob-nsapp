package com.mobiquity.mobtravelapp.model.travel;

import lombok.*;

import java.util.List;

@Getter
@Builder
public class Route {
    private final int index;
    private final int plannedDurationInMinutes;
    private final int transfers;
    private final String status;
    private final List<Leg> legs;

}
