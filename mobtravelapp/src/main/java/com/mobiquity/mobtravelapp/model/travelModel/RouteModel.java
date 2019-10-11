package com.mobiquity.mobtravelapp.model.travelModel;

import lombok.*;


@Builder
@Getter
public class RouteModel {
    private String fromStation;
    private String toStation;
    private String dateTime;
    private int routeLimit;
}
