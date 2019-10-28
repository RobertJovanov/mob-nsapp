package com.mobiquity.mobtravelapp.model.travel;

import lombok.*;

@Builder
@Getter
public class Station {
    private final String name;
   private final String latitude;
   private final String longitude;

}
