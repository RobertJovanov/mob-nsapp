package com.mobiquity.mobtravelapp.model.travel;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RouteModel {
    private String fromStation;
    private String toStation;
    private String dateTime;

}
