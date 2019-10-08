package com.mobiquity.mobtravelapp.model;

import java.util.ArrayList;
import java.util.List;

public class Route {
    int index;
    Station origin;
    Station destination;
    Station direction;
    List<Station> stops;
    int transfers;

    private Route(int index, Station origin, Station destination, Station direction, List<Station> stops, int transfers){
        this.index = index;
        this.origin = origin;
        this.destination = destination;
        this.direction = direction;
        this.stops = stops;
        this.transfers = transfers;
    }

    public static Route createRouteWithoutStops(int index, Station origin, Station destination){
        /**
         * Destination and Origin will be the same station in this case.
         */
        return new Route(index, origin, destination, destination, new ArrayList<>(), 0);
    }

    public static Route createRoute(int index, Station origin, Station destination, Station direction,
                                    List<Station> stops, int transfers){
        return new Route(index, origin, destination, direction, stops, transfers);
    }

}
