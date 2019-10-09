package com.mobiquity.mobtravelapp.model;

public class Station {
    private String name;
    private String plannedDepartureTime;
    private String actualDepartureTime;
    private String plannedArrivalTime;
    private String actualArrivalTime;
    private String plannedTrack;
    private String actualTrack;
    //TODO implement passing

    private Station(String name, String plannedArrivalTime, String actualArrivalTime, String plannedDepartureTime,
                    String actualDepartureTime, String plannedTrack, String actualTrack){
        this.name = name;
        this.plannedArrivalTime = plannedArrivalTime;
        this.actualArrivalTime = actualArrivalTime;
        this.plannedDepartureTime = plannedDepartureTime;
        this.actualDepartureTime = actualDepartureTime;
        this.plannedTrack = plannedTrack;
        this.actualTrack = actualTrack;
    }

    public static Station createOriginStation(String name, String plannedDateTime, String actualDateTime,
                                              String plannedTrack, String actualTrack){
        return new Station(name, "", "", plannedDateTime, actualDateTime,
                plannedTrack, actualTrack);
    }

    public static Station createDestinationStation(String name, String plannedDateTime, String actualDateTime,
                                                   String plannedTrack, String actualTrack){
        return new Station(name, plannedDateTime, actualDateTime, "", "",
                plannedTrack, actualTrack);
    }

    public static Station createStopStation(String name, String plannedArrivalTime, String actualArrivalTime,
                                            String plannedDepartureTime, String actualDepartureTime,
                                            String plannedTrack, String actualTrack){
        return new Station(name, plannedArrivalTime, actualArrivalTime, plannedDepartureTime, actualDepartureTime,
                plannedTrack, actualTrack);
    }

    public static Station createDirectionStation(String name){
        return new Station(name, "", "", "", "", "", "");
    }

}
