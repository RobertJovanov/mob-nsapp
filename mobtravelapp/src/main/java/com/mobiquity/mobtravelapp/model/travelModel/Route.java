package com.mobiquity.mobtravelapp.model.travelModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Route {
    private int index;
    private int PlannedDurationInMinutes;
    private int transfers;
    private String status;
    private List<Leg> legs;

}
