package com.mobiquity.mobtravelapp.model.travel;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RouteModel {
    private String fromStation;
    private String toStation;
    private String dateTime;
    private String searchForArrival;

    public RouteModel(String fromStation, String toStation, String dateTime){
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.dateTime = dateTime;
        this.searchForArrival = "false";
    }
}
