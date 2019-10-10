package com.mobiquity.mobtravelapp.service;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mobiquity.mobtravelapp.model.Route;
import com.mobiquity.mobtravelapp.model.RouteModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.constraints.AssertTrue;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
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

    @Test
    public void checkIfStationIsSerialized(){
        JsonArray jsonArray = getJsonArrayFromTestResource();
//        Station expectedStation = Station.createOriginStation("Amsterdam Zuid", "2019-10-09T09:15:00+0200",
//                "2019-10-09T09:15:00+0200", "2", "2");
//        assertThat(expectedStation, travelService.extractOriginStation(jsonArray));
    }

    @Test
    public void checkRoutesExtractionSuccessful(){
        JsonArray jsonArray = getJsonArrayFromTestResource();
        JsonObject jsonObject = new JsonParser().parse(String.valueOf(jsonArray)).getAsJsonObject();
        JsonArray trips = jsonObject.getAsJsonArray("trips");
        List<Route> expectedRoutes= travelService.extractingAllTheRoutes(trips);
        assertEquals(6,expectedRoutes.size());

    }

    private JsonArray getJsonArrayFromTestResource() {
        Stream<String> jsonContent = null;
        try {
            jsonContent = Files.lines(Paths.get("src/test/resources/jsonTest.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonString = jsonContent.collect(Collectors.joining());
        return travelService.extractingAllTheTrips(jsonString);
    }

}
