package com.mobiquity.mobtravelapp.model;

import java.util.List;

public class Leg {

    private Station  origin;
    private Station  destination;
    private String  direction;
    private List<Station> stops;

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
}
