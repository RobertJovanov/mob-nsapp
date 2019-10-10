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
        this.passing = passing;
    }

    /**
     * The station of origin only contains values for times of departure
     * @param name
     * @param plannedDateTime
     * @param actualDateTime
     * @param plannedTrack
     * @param actualTrack
     * @return
     */
    public static Station createOriginStation(String name, String plannedDateTime, String actualDateTime,
                                              String plannedTrack, String actualTrack){
        return new Station(name, "", "", plannedDateTime, actualDateTime,
                plannedTrack, actualTrack, false);
    }

    /**
     * The destination station only contains values for times of arrival
     * @param name
     * @param plannedDateTime
     * @param actualDateTime
     * @param plannedTrack
     * @param actualTrack
     * @return
     */

    public static Station createDestinationStation(String name, String plannedDateTime, String actualDateTime,
                                                   String plannedTrack, String actualTrack){
        return new Station(name, plannedDateTime, actualDateTime, "", "",
                plannedTrack, actualTrack, false);
    }

    public static Station createStopStation(String name, String plannedArrivalTime, String actualArrivalTime,
                                            String plannedDepartureTime, String actualDepartureTime,
                                            String plannedTrack, String actualTrack){
        return new Station(name, plannedArrivalTime, actualArrivalTime, plannedDepartureTime, actualDepartureTime,
                plannedTrack, actualTrack, false);
    }

    /**
     * If a train does not stop at a station then the passing value is set to true
     * @param name
     * @return
     */
    public static Station createPassingStation(String name){
        return new Station(name, "", "", "", "", "", "", true);
    }

    public static Station createDirectionStation(String name){
        return new Station(name, "", "", "", "", "", "", false);
    }

}
