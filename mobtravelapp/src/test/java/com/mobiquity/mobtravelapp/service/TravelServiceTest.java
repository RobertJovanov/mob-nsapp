package com.mobiquity.mobtravelapp.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mobiquity.mobtravelapp.exception.IncorrectFormatException;
import com.mobiquity.mobtravelapp.exception.WeatherException;
import com.mobiquity.mobtravelapp.model.travel.Route;
import com.mobiquity.mobtravelapp.model.travel.RouteModel;
import com.mobiquity.mobtravelapp.model.travel.Station;
import com.mobiquity.mobtravelapp.model.weather.Weather;
import com.mobiquity.mobtravelapp.validation.TravelValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TravelServiceTest {

    @InjectMocks
    private final TravelService travelService = new TravelService();

    @Mock
    private final WeatherService weatherService = new WeatherService();

    @Mock
    private final NsService nsService = new NsService();

    RouteModel routeModel = new RouteModel("Amsterdam Zuid", "Duivendrecht", "2019-10-09T12:30:00");

    @BeforeEach
    public void init() throws WeatherException, IncorrectFormatException {
        when(weatherService.getWeather(any(Station.class), any())).thenReturn(new Weather());
        when(nsService.getNsTrips(any(RouteModel.class))).thenReturn(getJsonArrayFromTestResource());
    }

    @Test
    public void checkIfInputIsCaseInsensitive() {
        String fromStation = TravelValidation.reformatStationName("amsterdam zuid");
        String toStation = TravelValidation.reformatStationName("duivendrecht");
        assertEquals("Amsterdam Zuid", fromStation);
        assertEquals("Duivendrecht", toStation);
    }

    @Test
    public void checkTimeFormatCorrectFormat() {
        assertTrue(TravelValidation.checkInputTime("2019-10-09T12:30:00Z"));
    }

    @Test
    public void checkTimeFormatIncorrectFormat() {
        assertFalse(TravelValidation.checkInputTime("noon"));
    }

    @Test
    public void getRoutes() throws IncorrectFormatException, WeatherException {

        assertNotNull(travelService.getTripFromNs(routeModel));
    }

    @Test
    @DisplayName("Should extract first station name")
    public void checkIfStationExtractionIsSuccessful() {
        JsonObject jsonObject = new JsonParser().parse(getJsonArrayFromStopTestResource()).getAsJsonObject();
        JsonArray stops = jsonObject.getAsJsonArray("stops");
        assertEquals("Amsterdam Zuid", travelService.extractStation(stops.get(0).getAsJsonObject()).getName());
        assertEquals("52.339027", travelService.extractStation(stops.get(0).getAsJsonObject()).getLatitude());
        assertEquals("4.873061", travelService.extractStation(stops.get(0).getAsJsonObject()).getLongitude());
    }

    @Test
    @DisplayName("stopsJson test resource contains one stop which we should extract")
    public void checkIfStopStubExtractionIsSuccessful() {
        JsonObject jsonObject = new JsonParser().parse(getJsonArrayFromStopTestResource()).getAsJsonObject();
        JsonArray stops = jsonObject.getAsJsonArray("stops");
        assertEquals(1, travelService.extractAllStops(stops).size());
    }

    @Test
    @DisplayName("legTest resource contains two legs which we should extract")
    public void checkIfLegExtractionIsSuccessful() throws Exception {
        JsonObject jsonObject = new JsonParser().parse(getJsonArrayFromLegTestResource()).getAsJsonObject();
        JsonArray leg = jsonObject.getAsJsonArray("legs");
        assertEquals(2, travelService.extractAllLegs(leg).size());
    }

    @Test
    @DisplayName("jsonTest resource contains six routes which we should should extract")
    public void checkIfRoutesExtractionIsSuccessful() {
        String trips = getJsonArrayFromTestResource();
        JsonArray expectedRoutes = travelService.extractAllTrips(trips);
        List<Route> routes = travelService.extractAllRoutes(expectedRoutes);
        assertEquals(6, routes.size());
    }

    //Helper methods
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

    private String getJsonArrayFromLegTestResource() {
        Stream<String> jsonContent = null;
        try {
            jsonContent = Files.lines(Paths.get("src/test/resources/legsTest.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonString = jsonContent.collect(Collectors.joining());
        return jsonString;
    }

}
