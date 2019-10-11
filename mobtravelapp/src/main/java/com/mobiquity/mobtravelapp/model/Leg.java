package com.mobiquity.mobtravelapp.model;
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
    private Station origin;
    private Station destination;
    private List<Station> stops;
}
