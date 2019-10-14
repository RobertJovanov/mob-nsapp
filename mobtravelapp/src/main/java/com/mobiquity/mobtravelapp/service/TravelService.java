package com.mobiquity.mobtravelapp.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mobiquity.mobtravelapp.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;


@Service
public class TravelService {

    private final Logger logger = LoggerFactory.getLogger(TravelService.class);

    @Value("${ns.nl.api.url}")
    private String uri;

    final String key = "7504c483d91f486a82b917743521ab40";

    public Trip getRoutes(RouteModel routeModel) {
        String url = MessageFormat.format(uri, "fromStation=" + routeModel.getFromStation(), "toStation=" + routeModel.getToStation(), "dateTime=" + routeModel.getDateTime());
        logger.info(url);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Ocp-Apim-Subscription-Key", key);

        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println(result.getBody());

        JsonArray trips = extractingAllTheTrips(result.getBody());
        return Trip.createTrip(routeModel.getFromStation(), routeModel.getToStation(), routeModel.getDateTime(), extractingAllTheRoutes(trips));
    }


    public JsonArray extractingAllTheTrips(String result) {
        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        JsonArray trips = jsonObject.getAsJsonArray("trips");
        return trips;
    }


    public List<Route> extractingAllTheRoutes(JsonArray trips) {
        List<Route> routes = new ArrayList<>();
        AtomicInteger index = new AtomicInteger(1);
        IntStream.range(0, trips.size()).mapToObj(i -> trips.get(i).getAsJsonObject()).forEach(trip -> {
            Route route = new Route();
            route.setIndex(index.getAndIncrement());
            route.setPlannedDurationInMinutes(trip.get("plannedDurationInMinutes").getAsInt());
            route.setTransfers(trip.get("transfers").getAsInt());
            route.setStatus(trip.get("status").getAsString());
            if (trip.get("status").getAsString().equals("CANCELLED")||trip.get("status").getAsString().equals("DISRUPTION")) {
                System.out.println("This Route is cancelled. Do nothing");
            } else {
                JsonArray legs = trip.getAsJsonArray("legs");
                route.setLegs( extractAllTheLeg(legs));
            }
            routes.add(route);

        });
        return routes;
    }



    public List<Leg> extractAllTheLeg(JsonArray legs){
        List<Leg> legList=new ArrayList<>();
        for(int j=0;j<legs.size();j++){
            Leg leg=new Leg();
            JsonObject legFromNs = legs.get(j).getAsJsonObject();
            leg.setDirection(legFromNs.get("direction").getAsString());
            JsonArray stops = legFromNs.get("stops").getAsJsonArray();
            List<Station> stations = extractAllStations(stops);
            leg.setOrigin(stations.get(0));
            leg.setDestination(stations.get(stations.size() - 1));
            List<Station> intermediateStation = new ArrayList<>();
            for (int i = 1; i < stations.size() - 1; i++) {
                intermediateStation.add(stations.get(i));
            }
            leg.setStops(intermediateStation);
            legList.add(leg);
        }
        return legList;
    }



    public List<Station> extractAllStations(JsonArray stops) {

        List<Station> stationList = new ArrayList<>();

        int stopSize = stops.size();
        IntStream.range(0, stopSize).forEach((i) -> {
            Station station = new Station();
            JsonObject intermediateStops = stops.get(i).getAsJsonObject();
            station.setName(intermediateStops.get("name").getAsString());
            if (!intermediateStops.has("passing")) {
                station.setPlannedArrivalTime(intermediateStops.get("plannedArrivalDateTime").getAsString());
                if (intermediateStops.has("actualArrivalDateTime")) {
                    station.setActualArrivalTime(intermediateStops.get("actualArrivalDateTime").getAsString());
                } else {
                    station.setActualArrivalTime(intermediateStops.get("plannedArrivalDateTime").getAsString());
                }
                if (i != stopSize - 1) {
                    if (intermediateStops.has("actualDepartureDateTime")) {
                        station.setActualDepartureTime(intermediateStops.get("actualDepartureDateTime").getAsString());
                    } else {
                        station.setActualDepartureTime(intermediateStops.get("plannedDepartureDateTime").getAsString());
                    }
                }
                station.setPlannedTrack(intermediateStops.get("plannedArrivalTrack").getAsString());

                if (intermediateStops.has("actualArrivalTrack")) {
                    station.setActualTrack(intermediateStops.get("actualArrivalTrack").getAsString());
                } else {
                    station.setActualTrack(intermediateStops.get("plannedArrivalTrack").getAsString());
                }
                stationList.add(station);
            }
        });
        return stationList;

    }


}




