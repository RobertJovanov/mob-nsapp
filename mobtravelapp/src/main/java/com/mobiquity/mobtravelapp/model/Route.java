package com.mobiquity.mobtravelapp.model;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private int index;
    private Station origin;
    private Station destination;
    private String direction;
    private List<Station> stops;
    private int transfers;
    private String status;
    private int plannedDurationInMinutes;
    private List<Route> transferRoutes;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<Route> getTransferRoutes() {
        return transferRoutes;
    }

    public void setTransferRoutes(List<Route> transferRoutes) {
        this.transferRoutes = transferRoutes;
    }

    public Route() {
    }


    private Route(Station origin, Station destination, String direction,
                  List<Station> stops, int transfers, String status, int plannedDurationInMinutes) {
//       this.index = index;
        this.origin = origin;
        this.destination = destination;
        this.direction = direction;
        this.stops = stops;
        this.transfers = transfers;
        this.status = status;
        this.plannedDurationInMinutes = plannedDurationInMinutes;
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


    public Station getOrigin() {
        return origin;
    }

    public void setOrigin(Station origin) {
        this.origin = origin;
    }

    public Station getDestination() {
        return destination;
    }

    public void setDestination(Station destination) {
        this.destination = destination;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public List<Station> getStops() {
        return stops;
    }

    public void setStops(List<Station> stops) {
        this.stops = stops;
    }

    public int getTransfers() {
        return transfers;
    }

    public void setTransfers(int transfers) {
        this.transfers = transfers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPlannedDurationInMinutes() {
        return plannedDurationInMinutes;
    }

    public void setPlannedDurationInMinutes(int plannedDurationInMinutes) {
        this.plannedDurationInMinutes = plannedDurationInMinutes;
    }

    @Override
    public String toString() {
        return "Route{" +
                "origin=" + origin +
                ", destination=" + destination +
                ", direction='" + direction + '\'' +
                ", stops=" + stops +
                ", transfers=" + transfers +
                ", status='" + status + '\'' +
                ", plannedDurationInMinutes=" + plannedDurationInMinutes +
                '}';
    }
}
