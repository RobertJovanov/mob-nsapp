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
public class Trip {
    private String origin;
    private String destination;
    private String dateTime;
    private List<Route> routes;

    public static Trip createTrip(String fromStation, String toStation, String dateTime, List<Route> extractingAllTheRoutes) {
        return new Trip(fromStation,toStation,dateTime,extractingAllTheRoutes);
    }
}
