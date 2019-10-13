package com.mobiquity.mobtravelapp.service;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mobiquity.mobtravelapp.model.travelModel.Route;
import com.mobiquity.mobtravelapp.model.travelModel.RouteModel;
import com.mobiquity.mobtravelapp.validation.TravelValidation;
import org.junit.Before;
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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TravelServiceTest {

    @Spy
    private final TravelService travelService = new TravelService();
    RouteModel routeModel =RouteModel.builder().fromStation("Amsterdam Zuid").toStation("duivendrecht").dateTime("2019-10-09T12:30:00Z").routeLimit(0).build();
    @Before
    public void setUp() {
        ReflectionTestUtils.setField(travelService, "uri", "https://gateway.apiportal.ns.nl/public-reisinformatie/api/v3/trips?{0}&{1}&{2}");
    }

    @Test
    public void getRoutes() {

        travelService.getRoutes(routeModel);
    }

    @Test
    public void checkingInputIsCaseInSensitive(){
        String fromStation=TravelValidation.checkInputStations(routeModel.getFromStation());
        String toStation=TravelValidation.checkInputStations(routeModel.getToStation());
        assertEquals("Amsterdam Zuid",fromStation);
        assertEquals("Duivendrecht",toStation);
    }
    @Test
    public void checkingTimeFormat(){
        assertEquals("2019-10-09T12:30:00Z",TravelValidation.checkInputTime(routeModel.getDateTime()));;
    }

    @Test
    @DisplayName("Json file should contain six routes when parsed")
    public void checkThatJsonIsParsed(){

        String jsonArray = getJsonArrayFromTestResource();

    }
   /* @Test

    public void checkStationExtractionSuccessful(){
        JsonArray jsonArray = getJsonArrayFromTestResource();
        List<Route> expectedRoutes= travelService.extractingAllTheRoutes(jsonArray);
        assertEquals(6,expectedRoutes.size());
    }*/

    @Test

    public void checkRoutesExtractionSuccessful(){
        String jsonArray = getJsonArrayFromTestResource();
        JsonArray expectedRoutes= travelService.extractingAllTrips(jsonArray);
        List<Route> routes=travelService.extractingAllRoutes(expectedRoutes);
        assertEquals(6,routes.size());
    }

   /* @Test
    public void checkStopExtractionSuccessful(){
        JsonObject jsonObject = new JsonParser().parse(getJsonArrayFromStopTestResource()).getAsJsonObject();
        JsonArray stops = jsonObject.getAsJsonArray("stops");
       // assertEquals(3,travelService.extractAllStations(stops).size());
    }*/


    private String getJsonArrayFromTestResource() {
        Stream<String> jsonContent = null;
        try {
            jsonContent = Files.lines(Paths.get("src/test/resources/jsonTest.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonString = jsonContent.collect(Collectors.joining());
        return jsonString;
    }

    private String getJsonArrayFromStopTestResource() {
        Stream<String> jsonContent = null;
        try {
            jsonContent = Files.lines(Paths.get("src/test/resources/stopsTest.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonString = jsonContent.collect(Collectors.joining());
        return jsonString;
    }
}
