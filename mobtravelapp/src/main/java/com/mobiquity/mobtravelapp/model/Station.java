package com.mobiquity.mobtravelapp.model;

public class Station {
    private String name;
    private String plannedDepartureTime;
    private String actualDepartureTime;
    private String plannedArrivalTime;
    private String actualArrivalTime;
    private String plannedTrack;
    private String actualTrack;

    public Station() {
    }

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



    //    public static Station createOriginStation(String name, String plannedDateTime, String actualDateTime,
//                                              String plannedTrack, String actualTrack){
//        return new Station(name, "", "", plannedDateTime, actualDateTime,
//                plannedTrack, actualTrack);
//    }
//
//    public static Station createDestinationStation(String name, String plannedDateTime, String actualDateTime,
//                                                   String plannedTrack, String actualTrack){
//        return new Station(name, plannedDateTime, actualDateTime, "", "",
//                plannedTrack, actualTrack);
//    }
//
//    public static Station createStopStation(String name, String plannedArrivalTime, String actualArrivalTime,
//                                            String plannedDepartureTime, String actualDepartureTime,
//                                            String plannedTrack, String actualTrack){
//        return new Station(name, plannedArrivalTime, actualArrivalTime, plannedDepartureTime, actualDepartureTime,
//                plannedTrack, actualTrack);
//    }
//


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlannedDepartureTime() {
        return plannedDepartureTime;
    }

    public void setPlannedDepartureTime(String plannedDepartureTime) {
        this.plannedDepartureTime = plannedDepartureTime;
    }

    public String getActualDepartureTime() {
        return actualDepartureTime;
    }

    public void setActualDepartureTime(String actualDepartureTime) {
        this.actualDepartureTime = actualDepartureTime;
    }

    public String getPlannedArrivalTime() {
        return plannedArrivalTime;
    }

    public void setPlannedArrivalTime(String plannedArrivalTime) {
        this.plannedArrivalTime = plannedArrivalTime;
    }

    public String getActualArrivalTime() {
        return actualArrivalTime;
    }

    public void setActualArrivalTime(String actualArrivalTime) {
        this.actualArrivalTime = actualArrivalTime;
    }

    public String getPlannedTrack() {
        return plannedTrack;
    }

    public void setPlannedTrack(String plannedTrack) {
        this.plannedTrack = plannedTrack;
    }

    public String getActualTrack() {
        return actualTrack;
    }

    public void setActualTrack(String actualTrack) {
        this.actualTrack = actualTrack;
    }


}
