package com.mobiquity.mobtravelapp.model;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
public class RouteModel {
    private String fromStation;
    private String toStation;
    private String dateTime;

    public RouteModel(String fromStation, String toStation, String dateTime) {
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.dateTime = dateTime;
    }

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
