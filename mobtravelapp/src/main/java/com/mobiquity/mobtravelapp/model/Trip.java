package com.mobiquity.mobtravelapp.model;

import java.util.List;

public class Trip {
    private String origin;
    private String destination;
    private String dateTime;
    private List<Route> routes;

    public Trip() {

    }

    private Trip(String origin, String destination, String dateTime, List<Route> routes){
        this.origin = origin;
        this.destination = destination;
        this.dateTime = dateTime;
        this.routes = routes;
    }

    public static Trip createTrip(String origin, String destination, String dateTime, List<Route> routes){
        return new Trip(origin, destination, dateTime, routes);
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
