package com.mobiquity.mobtravelapp.service;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mobiquity.mobtravelapp.model.Route;
import com.mobiquity.mobtravelapp.model.RouteModel;
import com.mobiquity.mobtravelapp.model.Station;
import com.mobiquity.mobtravelapp.model.Trip;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TravelServiceTest {

    @Spy
    private final TravelService travelService = new TravelService();

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(travelService, "uri", "https://gateway.apiportal.ns.nl/public-reisinformatie/api/v3/trips?{0}&{1}&{2}");
    }

    @Test
    public void getRoutes() {
        RouteModel routeModel = new RouteModel("Amsterdam Zuid", "Duivendrecht", "2019-10-09 12:30:00");
        travelService.getRoutes(routeModel);
    }

    @Test
    @DisplayName("Json file should contain six routes when parsed")
    public void checkThatJsonIsParsed(){
        JsonArray jsonArray = new JsonArray();
        jsonArray = getJsonArrayFromTestResource();
        assertEquals(6, jsonArray.size());
    }

    @Ignore
    @Test
    public void checkIfStationIsSerialized(){
        JsonArray jsonArray = getJsonArrayFromTestResource();
        Station expectedStation = Station.createOriginStation("Amsterdam Zuid", "2019-10-09T09:15:00+0200",
                "2019-10-09T09:15:00+0200", "2", "2");
        //assertThat(expectedStation, travelService.extractOriginStation(jsonArray));
    }

    @Test
    public void checkIfTripIsSerialized(){
        JsonArray jsonArray = getJsonArrayFromTestResource();

        String tripOrigin;
        String tripDestination;
        String tripDateTime;

        List<Route> expectedRoutes = new ArrayList<>();

        IntStream.range(0, jsonArray.size()).mapToObj(i -> jsonArray.get(i).getAsJsonObject()).forEach(trip -> {

            JsonArray legs = trip.getAsJsonArray("legs");

            List<Station> stopStations = new ArrayList<>();

            JsonObject leg = legs.get(0).getAsJsonObject();

            JsonArray stops = leg.get("stops").getAsJsonArray();

            /**
             * Create the origin station of a route
             */
            JsonObject origin = stops.get(0).getAsJsonObject();

            String actualOriginDepartureDateTime;
            if (origin.get("actualDepartureDateTime") != null) {
                actualOriginDepartureDateTime = origin.get("actualDepartureTime").getAsString();
            }else {
                actualOriginDepartureDateTime = origin.get("plannedDepartureDateTime").getAsString();
            }
            String actualOriginTrack;
            if(origin.get("actualTrack")!=null){
                actualOriginTrack = origin.get("actualTrack").getAsString();
            }else{
                actualOriginTrack = origin.get("plannedTrack").getAsString();
            }

            Station originStation = Station.createOriginStation(origin.get("name").getAsString(),
                    origin.get("plannedDepartureDateTime").getAsString(), actualOriginDepartureDateTime,
                    origin.get("plannedTrack").getAsString(), actualOriginTrack);

            /**
             * Add all stops along a route
             */
            for(int i = 1; i < stops.size() - 1; i++){
                JsonObject stop = stops.get(i).getAsJsonObject();
                String actualStopArrivalTime;
                if(stop.get("actualArrivalDateTime") != null){
                    actualStopArrivalTime = stop.get("actualArrivalDateTime").getAsString();
                }else {
                    actualStopArrivalTime = stop.get("plannedArrivalDateTime").getAsString();
                }
                String actualStopDepartureDateTime;
                if (origin.get("actualDepartureDateTime") != null) {
                    actualStopDepartureDateTime = origin.get("actualDepartureTime").getAsString();
                }else {
                    actualStopDepartureDateTime = origin.get("plannedDepartureDateTime").getAsString();
                }
                String actualStopTrack;
                if(origin.get("actualTrack")!=null){
                    actualStopTrack = origin.get("actualTrack").getAsString();
                }else{
                    actualStopTrack = origin.get("plannedTrack").getAsString();
                }
                Station stopStation = Station.createStopStation(stop.get("name").getAsString(),
                        stop.get("plannedArrivalDateTime").getAsString(), actualStopArrivalTime,
                        stop.get("plannedDepartureDateTime").getAsString(), actualStopDepartureDateTime,
                        stop.get("plannedTrack").getAsString(), actualStopTrack);

                stopStations.add(stopStation);
            }

            /**
             * Add the destination of a route
             */
            JsonObject destination = stops.get(stops.size()-1).getAsJsonObject();

            String actualDestinationArrivalTime;
            if(destination.get("actualArrivalDateTime") != null){
                actualDestinationArrivalTime = destination.get("actualArrivalDateTime").getAsString();
            }else {
                actualDestinationArrivalTime = destination.get("plannedArrivalDateTime").getAsString();
            }
            String actualDestinationTrack;
            if(origin.get("actualTrack")!=null){
                actualDestinationTrack = origin.get("actualTrack").getAsString();
            }else{
                actualDestinationTrack = origin.get("plannedTrack").getAsString();
            }

            Station destinationStation = Station.createDestinationStation(destination.get("name").getAsString(),
                    destination.get("plannedArrivalDateTime").getAsString(), actualDestinationArrivalTime,
                    destination.get("plannedTrack").getAsString(), actualDestinationTrack);

            /**
             * Extract the direction, number of transfers and status of a trip
             */
            Station directionStation = Station.createDirectionStation(leg.get("direction").getAsString());
            int index = leg.get("idx").getAsInt();
            int transfers = trip.get("transfers").getAsInt();
            String status = trip.get("status").getAsString();

            Route route = Route.createRoute(index, originStation, destinationStation,
                    directionStation, stopStations, transfers, status);

            expectedRoutes.add(route);
        });

        //TODO create trip and assertIt
        //TODO set trip origin, destination and dateTime
        Trip expectedTrip = Trip.createTrip("","","", expectedRoutes);

        assertEquals(expectedTrip, travelService.extractTrip());
    }

    private JsonArray getJsonArrayFromTestResource() {
        Stream<String> jsonContent = null;
        try {
            jsonContent = Files.lines(Paths.get("src/test/resources/jsonTest.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonString = jsonContent.collect(Collectors.joining());
        return travelService.parseJson(jsonString);
    }

}
