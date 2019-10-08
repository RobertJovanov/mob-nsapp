package com.mobiquity.mobtravelapp.model;

import java.util.List;

public class Trip {
    private String origin;
    private String destination;
    private String dateTime;
    private List<Route> routes;

    private Trip(String origin, String destination, String dateTime, List<Route> routes){
        this.origin = origin;
        this.destination = destination;
        this.dateTime = dateTime;
        this.routes = routes;
    }

    public static Trip createTrip(String origin, String destination, String dateTime, List<Route> routes){
        return new Trip(origin, destination, dateTime, routes);
    }
}
