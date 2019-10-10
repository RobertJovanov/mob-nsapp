package com.mobiquity.mobtravelapp.model;

import java.util.List;

/**
 * A Leg represents a leg of a route
 */
public class Leg {
    private Station origin;
    private Station destination;
    private Station direction;
    private List<Station> stops;

    private Leg(Station origin, Station destination, Station direction, List<Station> stops){
        this.origin = origin;
        this.destination = destination;
        this.direction = direction;
        this.stops = stops;
    }

    public static Leg createLeg(Station origin, Station destination, Station direction, List<Station> stops){
        return new Leg(origin, destination, direction, stops);
    }

}
