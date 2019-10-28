package com.mobiquity.mobtravelapp.model.travel;
import lombok.*;

import java.util.List;

@Getter
@Builder
public class Leg {
    private final String direction;
    private final OriginStub origin;
    private final DestinationStub destination;
    private final List<StopStub> stops;


}
