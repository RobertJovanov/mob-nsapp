package com.mobiquity.mobtravelapp.model;

import java.util.List;

public class Route {

    private Station origin;
    private Station destination;
    private Station direction;
    private List<Station> stops;
    private int transfers;
    private String status;

    private Route(Station origin, Station destination, Station direction,
                  List<Station> stops, int transfers, String status){
        this.origin = origin;
        this.destination = destination;
        this.direction = direction;
        this.stops = stops;
        this.transfers = transfers;
        this.status = status;
    }

    public static Route createRoute(Station origin, Station destination, Station direction,
                                    List<Station> stops, int transfers, String status){
        return new Route(origin, destination, direction, stops, transfers, status);
    }



}
