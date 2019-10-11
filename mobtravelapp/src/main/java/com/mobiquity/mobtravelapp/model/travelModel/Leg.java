package com.mobiquity.mobtravelapp.model.travelModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class Leg {
    private String direction;
    private OriginStub origin;
    private DestinationStub destination;
    private List<StopStub> stops;


}
