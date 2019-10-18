package com.mobiquity.mobtravelapp.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mobiquity.mobtravelapp.exception.IncorrectFormatException;
import com.mobiquity.mobtravelapp.exception.WeatherException;
import com.mobiquity.mobtravelapp.model.travelModel.*;
import com.mobiquity.mobtravelapp.validation.TravelValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Service
public class TravelService {


    @Autowired
    private WeatherService weatherService;

    @Autowired
    private NsService nsService;


    /**
     * Reformat the values of a RouteModel to adhere to our format standard.
     * Standard: Stations should start with capital letter.
     * If a station name contains multiple words, each word should start with capital letter.
     *
     * @param routeModel from getTripFromNs
     * @return reformated routemodel
     * @throws IncorrectFormatException if the time is not in correct format
     */
    public RouteModel reformatRoutes(RouteModel routeModel) throws IncorrectFormatException {
        if (!TravelValidation.checkInputTime(routeModel.getDateTime())) {
            throw new IncorrectFormatException("Date Time should be formatted as: yyyy-mm-dd'T'HH:MM:ss");
        }
        return new RouteModel(TravelValidation.reformatStationName(routeModel.getFromStation()), TravelValidation.reformatStationName(routeModel.getToStation())
                , routeModel.getDateTime());
    }

    /**
     * Gets all trips
     * Validates and reformats parameters
     *
     * @param routeModel from the controller
     * @return Trip model which has list of routes
     * @throws IncorrectFormatException if request parameters for ns api are incorrect
     */
    public Trip getTripFromNs(RouteModel routeModel) throws IncorrectFormatException {
        RouteModel routeModelAfterReformat = reformatRoutes(routeModel);
        JsonArray trips = extractAllTrips(nsService.getNsTrips(routeModelAfterReformat));
        return new Trip(routeModelAfterReformat.getFromStation(), routeModelAfterReformat.getToStation(), routeModelAfterReformat.getDateTime(), extractAllRoutes(trips));

    }

    /**
     * Extracts all trips from reading the result from ns.nl
     *
     * @param result a String of trips
     * @return a JsonArray of trips
     */
    public JsonArray extractAllTrips(String result) {
        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        JsonArray trips = jsonObject.getAsJsonArray("trips");
        return trips;
    }

    /**
     * Extract all routes from trips
     *
     * @param trips a JsonArray of trips
     * @return List of Routes
     */
    public List<Route> extractAllRoutes(JsonArray trips) {
        List<Route> routes = new ArrayList<>();
        AtomicInteger index = new AtomicInteger(1);
        IntStream.range(0, trips.size()).mapToObj(i -> trips.get(i).getAsJsonObject()).forEach(trip -> {
            if (!trip.get("status").getAsString().equals("NORMAL")) {
                Route route = Route.builder()
                        .index(index.getAndIncrement())
                        .status(trip.get("status").getAsString())
                        .build();

                routes.add(route);
            } else {
                Route route = null;
                try {
                    route = Route.builder()
                            .index(index.getAndIncrement())
                            .plannedDurationInMinutes(trip.get("plannedDurationInMinutes").getAsInt())
                            .transfers(trip.get("transfers").getAsInt())
                            .status(trip.get("status").getAsString())
                            .legs(extractAllLegs(trip.get("legs").getAsJsonArray()))
                            .build();
                } catch (WeatherException e) {
                    e.printStackTrace();
                }


                routes.add(route);
            }
        });
        return routes;
    }

    /**
     * Extracts all legs from JsonArray of legs
     *
     * @param legArray a JsonArray of legs
     * @return List of legs
     */
    public List<Leg> extractAllLegs(JsonArray legArray) throws WeatherException {
        List<Leg> legs = new ArrayList<>();
        for (int i = 0; i < legArray.size(); i++) {
            JsonObject legAsJsonObject = legArray.get(i).getAsJsonObject();
            Leg leg = Leg.builder()
                    .direction(legAsJsonObject.get("direction").getAsString())
                    .origin(extractOriginStub(legAsJsonObject.get("stops").getAsJsonArray()))
                    .destination(extractDestinationStub(legAsJsonObject.get("stops").getAsJsonArray()))
                    .stops(extractAllStops(legAsJsonObject.get("stops").getAsJsonArray()))
                    .build();
            legs.add(leg);
        }
        return legs;
    }

    /**
     * Extracts origin stub from JsonArray of stops
     *
     * @param stops a JsonArray of stops
     * @return originStub
     */
    public OriginStub extractOriginStub(JsonArray stops) throws WeatherException {
        JsonObject jsonObject = stops.get(0).getAsJsonObject();
        return OriginStub.builder()
                .actualDepartureDateTime(setActualDepartureTime(jsonObject))
                .plannedDepartureDateTime(jsonObject.get("plannedDepartureDateTime").getAsString())
                .actualArrivalTrack(setActualTrack(jsonObject))
                .plannedArrivalTrack(setPlannedTrack(jsonObject))
                .station(extractStation(jsonObject))
                .weather(weatherService.getWeather(extractStation(jsonObject), jsonObject.get("plannedDepartureDateTime").getAsString()))
                .build();
    }

    /**
     * Extract list of intermediate stops from jsonArray of stops
     *
     * @param stops a JsonArray of stops
     * @return List of stopStubs
     */
    public List<StopStub> extractAllStops(JsonArray stops) {
        List<StopStub> legList = new ArrayList<>();
        IntStream.range(1, stops.size() - 1).mapToObj(i -> stops.get(i).getAsJsonObject()).forEach(stop -> {
            if (!stop.has("passing")) {
                StopStub stopStub = StopStub.builder()
                        .actualArrivalDateTime(setActualArrivalTime(stop.getAsJsonObject()))
                        .plannedArrivalDateTime(stop.get("plannedArrivalDateTime").getAsString())
                        .actualDepartureDateTime(setActualDepartureTime(stop.getAsJsonObject()))
                        .plannedDepartureDateTime(stop.get("plannedDepartureDateTime").getAsString())
                        .actualArrivalTrack(setActualTrack(stop.getAsJsonObject()))
                        .plannedArrivalTrack(setPlannedTrack(stop.getAsJsonObject()))
                        .station(extractStation(stop.getAsJsonObject()))
                        .build();

                legList.add(stopStub);
            }
        });
        return legList;
    }

    /**
     * Extracts destination stub from JsonArray of stops
     *
     * @param stops a JsonArray of stops
     * @return destinationStub  Details
     */
    public DestinationStub extractDestinationStub(JsonArray stops) throws WeatherException {
        JsonObject jsonObject = stops.get(stops.size() - 1).getAsJsonObject();
        return DestinationStub.builder()
                .actualArrivalDateTime(setActualArrivalTime(jsonObject))
                .plannedArrivalDateTime(jsonObject.get("plannedArrivalDateTime").getAsString())
                .actualArrivalTrack(setActualTrack(jsonObject))
                .plannedArrivalTrack(setPlannedTrack(jsonObject))
                .station(extractStation(jsonObject))
                .weather(weatherService.getWeather(extractStation(jsonObject), jsonObject.get("plannedArrivalDateTime").getAsString()))
                .build();
    }

    /**
     * Extract station details from JsonObject stations
     *
     * @param stations JsonObject
     * @return Station object
     */
    public Station extractStation(JsonObject stations) {
        return Station.builder()
                .name(stations.get("name").getAsString())
                .latitude(stations.get("lat").getAsString())
                .longitude(stations.get("lng").getAsString())
                .build();
    }

    // Helper methods
    private String setActualDepartureTime(JsonObject jsonObject) {
        if (jsonObject.has("actualDepartureDateTime")) {
            return jsonObject.get("actualDepartureDateTime").getAsString();
        } else {
            return jsonObject.get("plannedDepartureDateTime").getAsString();
        }
    }

    private String setActualArrivalTime(JsonObject jsonObject) {
        if (jsonObject.has("actualArrivalDateTime")) {
            return jsonObject.get("actualArrivalDateTime").getAsString();
        } else {
            return jsonObject.get("plannedArrivalDateTime").getAsString();
        }
    }

    private String setActualTrack(JsonObject jsonObject) {
        if (jsonObject.has("actualArrivalTrack")) {
            return jsonObject.get("actualArrivalTrack").getAsString();
        } else if (jsonObject.has("plannedArrivalTrack")) {
            return jsonObject.get("plannedArrivalTrack").getAsString();
        } else {
            return null;
        }
    }

    private String setPlannedTrack(JsonObject jsonObject) {
        if (jsonObject.has("plannedArrivalTrack")) {
            return jsonObject.get("plannedArrivalTrack").getAsString();
        } else {
            return null;
        }
    }

}




