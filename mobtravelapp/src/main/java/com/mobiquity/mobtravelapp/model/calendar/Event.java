package com.mobiquity.mobtravelapp.model.calendar;

import lombok.Getter;

@Getter
public class Event {
    private String id;
    private String location;
    private Start start;

    @Getter
    public class Start{
        private String date;
        private String dateTime;
    }
}
