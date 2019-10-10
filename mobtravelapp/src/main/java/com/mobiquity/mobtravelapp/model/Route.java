package com.mobiquity.mobtravelapp.model;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private int index;
    private Station origin;
    private Station destination;
    private String direction;
    private List<Leg> legs;
    private int transfers;
    private int plannedDuration;
    private String status;

    public Route(int index, Station origin, Station destination, String direction, List<Leg> legs, int transfers, int plannedDuration, String status) {
        this.index = index;
        this.origin = origin;
        this.destination = destination;
        this.direction = direction;
        this.legs = legs;
        this.transfers = transfers;
        this.plannedDuration = plannedDuration;
        this.status = status;
    }

    public void setIndex(int index) {
        this.index = index;
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

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    public void setTransfers(int transfers) {
        this.transfers = transfers;
    }

    public void setPlannedDuration(int plannedDuration) {
        this.plannedDuration = plannedDuration;
    }

    public void setStatus(String status) {
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
