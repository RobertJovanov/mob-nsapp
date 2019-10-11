package com.mobiquity.mobtravelapp.model.travelModel;

import lombok.*;


@Builder
@Getter
public class Station {
    private String name;
   private String latitude;
   private String longitude;

}
