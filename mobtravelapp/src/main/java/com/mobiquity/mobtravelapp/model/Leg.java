package com.mobiquity.mobtravelapp.model;

import java.util.List;

public class Leg {
    private String direction;
    private Station origin;
    private Station destination;
    private List<Station> stops;

    public Leg() {
    }

    public Leg(Station origin, Station destination, String direction, List<Station> stops) {
        this.origin = origin;
        this.destination = destination;
        this.direction = direction;
        this.stops = stops;
    }

    public void setOrigin(Station origin) {
        this.origin = origin;
    }

    public void setDestination(Station destination) {
        this.destination = destination;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setStops(List<Station> stops) {
        this.stops = stops;
    }

    public Station getOrigin() {
        return origin;
    }

    public Station getDestination() {
        return destination;
    }

    public String getDirection() {
        return direction;
    }

    public List<Station> getStops() {
        return stops;
    }
}
