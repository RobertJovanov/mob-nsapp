package com.mobiquity.mobtravelapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RouteModel {
    private String fromStation;
    private String toStation;
    private String dateTime;
}
