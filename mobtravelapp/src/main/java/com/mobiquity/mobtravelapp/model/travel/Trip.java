package com.mobiquity.mobtravelapp.model.travel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Trip {
    private String origin;
    private String destination;
    private String dateTime;
    private List<Route> routes;

}
