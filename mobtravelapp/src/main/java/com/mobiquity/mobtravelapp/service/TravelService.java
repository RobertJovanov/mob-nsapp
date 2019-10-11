package com.mobiquity.mobtravelapp.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mobiquity.mobtravelapp.model.travelModel.*;
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

        JsonArray trips = extractingAllTrips(result.getBody());
        return Trip.createTrip(routeModel.getFromStation(), routeModel.getToStation(), routeModel.getDateTime(), extractingAllRoutes(trips));
    }


    public JsonArray extractingAllTrips(String result) {
        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        JsonArray trips = jsonObject.getAsJsonArray("trips");
        return trips;
    }


    public List<Route> extractingAllRoutes(JsonArray trips) {
        List<Route> routes = new ArrayList<>();
        AtomicInteger index = new AtomicInteger(1);
        IntStream.range(0, trips.size()).mapToObj(i -> trips.get(i).getAsJsonObject()).forEach(trip -> {
            if(trip.get("status").getAsString().equals("CANCELLED")){
                Route route = Route.builder()
                        .index(index.getAndIncrement())
                        .status(trip.get("status").getAsString())
                        .build();

                routes.add(route);
            }else {
                Route route = Route.builder()
                        .index(index.getAndIncrement())
                        .plannedDurationInMinutes(trip.get("plannedDurationInMinutes").getAsInt())
                        .transfers(trip.get("status").getAsInt())
                        .status(trip.get("status").getAsString())
                        .legs(extractAllLegs(trip.get("legs").getAsJsonArray()))
                        .build();

                routes.add(route);
            }
        });
        return routes;
    }


        public List<StopStub> extractAllTheStops(JsonArray legs){
            List<StopStub> legList = new ArrayList<>();
            IntStream.range(1, legs.size()-1).mapToObj(i -> legs.get(i).getAsJsonObject()).forEach(leg -> {
                 StopStub stopStub = StopStub.builder()
                        .actualArrivalTime(leg.get("actualArrivalDateTime").getAsString())
                        .plannedArrivalTime(leg.get("plannedArrivalTime").getAsString())
                        .actualDepartureTime(leg.get("actualDepartureTime").getAsString())
                        .plannedDepartureTime(leg.get("plannedDepartureTime").getAsString())
                        .actualTrack(leg.get("actualTrack").getAsString())
                        .plannedTrack(leg.get("plannedTrack").getAsString())
                        .station(extractStation(leg.get("station")))
                        .build();

                 legList.add(stopStub);
            });

            return legList;
    }

    //TODO see if we need this, and check the json hier.
    public Station extractStation(JsonArray stations){
        JsonObject jsonObject = stations.get(0).getAsJsonObject();
        return Station.builder()
                .name(jsonObject.get("name").getAsString())
                .latitude(jsonObject.get("lat").getAsString())
                .longitude(jsonObject.get("lng").getAsString())
                .build();
    }


    public OriginStub extractOriginStub(JsonArray legs) {
        JsonObject jsonObject = legs.get(0).getAsJsonObject();
        return  OriginStub.builder()
                .actualDepartureTime(jsonObject.get("actualDepartureTime").getAsString())
                .plannedDepartureTime(jsonObject.get("plannedDepartureTime").getAsString())
                .actualTrack(jsonObject.get("actualTrack").getAsString())
                .plannedTrack(jsonObject.get("plannedTrack").getAsString())
                .station(extractStation(legs))
                .build();
    }


    public DestinationStub extarctDestinationStub(JsonArray legs){
        JsonObject jsonObject = legs.get(legs.size()-1).getAsJsonObject();
        return DestinationStub.builder()
                .actualArrivalTime(jsonObject.get("actualArrivalTime").getAsString())
                .plannedArrivalTime(jsonObject.get("plannedArrivalTime").getAsString())
                .actualTrack(jsonObject.get("actualTrack").getAsString())
                .plannedTrack(jsonObject.get("plannedTrack").getAsString())
                .station(extractStation(legs.get(0)))
                .build();
    }

    public List<Leg> extractAllLegs(JsonArray trips){

        List<Leg> legs = new ArrayList<>();
        IntStream.range(0,trips.size()).mapToObj(i -> trips.get(i).getAsJsonObject()).forEach(trip -> {
           Leg leg = Leg.builder()
                  .direction(trip.get("direction").getAsString())
                   .origin(extractOriginStub(trip.get("legs").getAsJsonArray()))
                   .destination(extarctDestinationStub(trip.get("legs").getAsJsonArray()))
                   .stops(extractAllTheStops(trip.get("legs").getAsJsonArray()))
                   .build();

            legs.add(leg);
        });

        return legs;
    }



//
//    public List<Station> extractAllStations(JsonArray stops) {
//
//        List<Station> stationList = new ArrayList<>();
//
//        int stopSize = stops.size();
//        IntStream.range(0, stopSize).forEach((i) -> {
//            Station station = new Station();
//            JsonObject intermediateStops = stops.get(i).getAsJsonObject();
//            station.setName(intermediateStops.get("name").getAsString());
//            if (intermediateStops.has("passing")) {
//
//            } else {
//                station.setPlannedArrivalTime(intermediateStops.get("plannedArrivalDateTime").getAsString());
//                if (intermediateStops.has("actualArrivalDateTime")) {
//                    station.setActualArrivalTime(intermediateStops.get("actualArrivalDateTime").getAsString());
//                } else {
//                    station.setActualArrivalTime(intermediateStops.get("plannedArrivalDateTime").getAsString());
//                }
//                if (i != stopSize - 1) {
//                    if (intermediateStops.has("actualDepartureDateTime")) {
//                        station.setActualDepartureTime(intermediateStops.get("actualDepartureDateTime").getAsString());
//                    } else {
//                        station.setActualDepartureTime(intermediateStops.get("plannedDepartureDateTime").getAsString());
//                    }
//                }
//                station.setPlannedTrack(intermediateStops.get("plannedArrivalTrack").getAsString());
//
//                if (intermediateStops.has("actualArrivalTrack")) {
//                    station.setActualTrack(intermediateStops.get("actualArrivalTrack").getAsString());
//                } else {
//                    station.setActualTrack(intermediateStops.get("plannedArrivalTrack").getAsString());
//                }
//                stationList.add(station);
//            }
//        });
//        return stationList;
//
//    }






}




