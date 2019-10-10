package com.mobiquity.mobtravelapp.model;

import java.util.List;

public class Route {
    private int index;
    private Station origin;
    private Station destination;
    private List<Leg> legs;
    private int transfers;
    private String status;
    private int PlannedDurationInMinutes;

    public Route() {
    }
    public Route(int index, Station origin, Station destination, List<Leg> legs, int transfers, String status, int PlannedDurationInMinutes) {
        this.index = index;
        this.origin = origin;
        this.destination = destination;

        this.legs = legs;
        this.transfers = transfers;
        this.status = status;
        this.PlannedDurationInMinutes=PlannedDurationInMinutes;;
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

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    public void setTransfers(int transfers) {
        this.transfers = transfers;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPlannedDurationInMinutes(int plannedDurationInMinutes) {
        PlannedDurationInMinutes = plannedDurationInMinutes;
    }
    //    public static Route createRouteWithoutStops(Station origin, Station destination, String status){
//        /**
//         * Destination and Origin will be the same station in this case.
//         */
//        return new Route(origin, destination, "", new ArrayList<>(), 0, status);
//    }
//
//    public static Route createRoute(Station origin, Station destination, String direction,
//                                    List<Station> stops, int transfers, String status){
//        return new Route(origin, destination, direction, stops, transfers, status);
//    }
//



}
