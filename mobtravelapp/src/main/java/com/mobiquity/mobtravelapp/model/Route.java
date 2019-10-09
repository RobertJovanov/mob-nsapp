package com.mobiquity.mobtravelapp.model;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private int index;
    private Station origin;
    private Station destination;
    private Station direction;
    private List<Station> stops;
    private int transfers;
    private String status;

    private Route(int index, Station origin, Station destination, Station direction,
                  List<Station> stops, int transfers, String status){
        this.index = index;
        this.origin = origin;
        this.destination = destination;
        this.direction = direction;
        this.stops = stops;
        this.transfers = transfers;
        this.status = status;
    }

    public static Route createRouteWithoutStops(int index, Station origin, Station destination, String status){
        /**
         * Destination and Origin will be the same station in this case.
         */
        return new Route(index, origin, destination, destination, new ArrayList<>(), 0, status);
    }

    public static Route createRoute(int index, Station origin, Station destination, Station direction,
                                    List<Station> stops, int transfers, String status){
        return new Route(index, origin, destination, direction, stops, transfers, status);
    }



}
