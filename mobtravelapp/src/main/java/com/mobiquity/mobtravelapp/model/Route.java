package com.mobiquity.mobtravelapp.model;

import java.util.List;

public class Route {
    private int index;
    private int PlannedDurationInMinutes;
    private int transfers;
    private String status;
    //    private Station origin;
//    private Station destination;
    private List<Leg> legs;


    public Route() {
    }

    public Route(int index, List<Leg> legs, int transfers, String status, int PlannedDurationInMinutes) {
        this.index = index;
        this.PlannedDurationInMinutes = PlannedDurationInMinutes;
        this.transfers = transfers;
        this.status = status;

//        this.origin = origin;
//        this.destination = destination;

        this.legs = legs;

    }

    public int getIndex() {
        return index;
    }

//    public Station getOrigin() {
//        return origin;
//    }
//
//    public Station getDestination() {
//        return destination;
//    }



    public int getTransfers() {
        return transfers;
    }

    public String getStatus() {
        return status;
    }

    public int getPlannedDurationInMinutes() {
        return PlannedDurationInMinutes;
    }
    public List<Leg> getLegs() {
        return legs;
    }

    public void setIndex(int index) {
        this.index = index;
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

//    public void setOrigin(Station origin) {
//        this.origin = origin;
//    }
//
//    public void setDestination(Station destination) {
//        this.destination = destination;
//    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
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
