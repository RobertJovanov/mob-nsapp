package com.mobiquity.mobtravelapp.model;

import java.util.List;

/**
 * A route represent a way to complete a trip
 */
public class Route {
    private int index;
    private Station origin;
    private Station destination;
    private List<Leg> stops;
    private int transfers;
    private String status;

    private Route(int index, Station origin, Station destination, List<Leg> stops, int transfers, String status){
        this.index = index;
        this.origin = origin;
        this.destination = destination;
        this.stops = stops;
        this.transfers = transfers;
        this.status = status;
    }

    public static Route createRoute(int index, Station origin, Station destination,
                                    List<Leg> stops, int transfers, String status){
        return new Route(index, origin, destination, stops, transfers, status);
    }

}
