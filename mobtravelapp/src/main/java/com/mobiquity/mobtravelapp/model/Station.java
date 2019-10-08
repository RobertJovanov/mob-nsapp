package com.mobiquity.mobtravelapp.model;

public class Station {
    private String name;
    private String plannedDepartureTime;
    private String actualDepartureTime;
    private String plannedArrivalTime;
    private String actualArrivalTime;
    private String plannedTrack;
    private String actualTrack;
    private boolean passing;

    private Station(String name, String plannedArrivalTime, String actualArrivalTime, String plannedDepartureTime,
                    String actualDepartureTime, String plannedTrack, String actualTrack, boolean passing){
        this.name = name;
        this.plannedArrivalTime = plannedArrivalTime;
        this.actualArrivalTime = actualArrivalTime;
        this.plannedDepartureTime = plannedDepartureTime;
        this.actualDepartureTime = actualDepartureTime;
        this.plannedTrack = plannedTrack;
        this.actualTrack = actualTrack;
        this.passing = passing; //If true then a train only passes through the station without stopping
    }

    public static Station createOriginStation(String name, String plannedDateTime, String actualDateTime,
                                              String plannedTrack, String actualTrack){
        return new Station(name, "", "", plannedDateTime, actualDateTime,
                plannedTrack, actualTrack, false);
    }

    public static Station createDestinationStation(String name, String plannedDateTime, String actualDateTime,
                                                   String plannedTrack, String actualTrack){
        return new Station(name, plannedDateTime, actualDateTime, "", "",
                plannedTrack, actualTrack, false);
    }

    public static Station createStopStation(String name, String plannedArrivalTime, String actualArrivalTime,
                                            String plannedDepartureTime, String actualDepartureTime,
                                            String plannedTrack, String actualTrack, boolean passing){
        return new Station(name, plannedArrivalTime, actualArrivalTime, plannedDepartureTime, actualDepartureTime,
                plannedTrack, actualTrack, passing);
    }

}
