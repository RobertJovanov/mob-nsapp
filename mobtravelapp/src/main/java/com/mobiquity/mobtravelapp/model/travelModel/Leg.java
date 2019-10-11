package com.mobiquity.mobtravelapp.model.travelModel;
import lombok.*;

import java.util.List;

@Getter
@Builder
public class Leg {
    private String direction;
    private OriginStub origin;
    private DestinationStub destination;
    private List<StopStub> stops;


}
