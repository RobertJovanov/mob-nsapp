package com.mobiquity.mobtravelapp.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mobiquity.mobtravelapp.model.RouteModel;
import com.mobiquity.mobtravelapp.model.Station;
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
import java.util.stream.IntStream;

;import static com.mobiquity.mobtravelapp.model.Station.createOriginStation;

@Service
public class TravelService {

    private final Logger logger = LoggerFactory.getLogger(TravelService.class);


    @Value("${ns.nl.api.url}")
    private String uri;

    final String key = "7504c483d91f486a82b917743521ab40";


    public void getRoutes(RouteModel routeModel) {
        String url = MessageFormat.format(uri, "fromStation=" + routeModel.getFromStation(), "toStation=" + routeModel.getToStation(), "dateTime=" + routeModel.getDateTime());
        RestTemplate restTemplate = new RestTemplate();
        // System.out.println(url);
        logger.info(url);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Ocp-Apim-Subscription-Key", key);

        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println(result.getBody());
        JsonArray trips = parseJson(result.getBody());
       // extractOriginStation(trips);

    }
    /*
    private void parseJson(String result) {
        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        JsonArray trips = jsonObject.getAsJsonArray("trips");
        System.out.println(trips.size());
        IntStream.range(0, trips.size()).mapToObj(i -> trips.get(i).getAsJsonObject()).forEach(trip -> {
            System.out.println(trip.get("plannedDurationInMinutes"));
            JsonArray legs = trip.getAsJsonArray("legs");
            IntStream.range(0, legs.size()).mapToObj(j -> legs.get(j).getAsJsonObject()).map(leg -> leg.get("direction")).forEach(System.out::println);
        });
        */

    public JsonArray parseJson(String result) {
        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        JsonArray trips = jsonObject.getAsJsonArray("trips");
        return trips;
    }

    //TODO Map Station
    // Extract data needed for factory methods

//    public Station extractOriginStation(JsonArray trips) {
//        IntStream.range(0, trips.size()).mapToObj(i -> trips.get(i).getAsJsonObject()).forEach(trip -> {
//            System.out.println(trip.get("plannedDurationInMinutes"));
//            JsonArray legs = trip.getAsJsonArray("legs");
//            int bound = legs.size();
//            for (int j = 0; j < bound; j++) {
//                JsonObject leg = legs.get(j).getAsJsonObject();
//                String direction = leg.get("direction").getAsString();
//                JsonArray stops = leg.get("stops").getAsJsonArray();
//                JsonObject stop = stops.get(0).getAsJsonObject();
//                String originName = stop.get("name").getAsString();
//                String actualDepartureDateTime;
//                String plannedDepartureTime = stop.get("plannedDepartureDateTime").getAsString();
//                if (stop.get("actualDepartureDateTime") != null) {
//                    actualDepartureDateTime = stop.get("actualDepartureTime").getAsString();
//                } else {
//                    actualDepartureDateTime = stop.get("plannedDepartureDateTime").getAsString();
//                }
//                String plannedTrack=stop.get("plannedTrack").getAsString();
//                String actualTrack;
//                if(stop.get("actualTrack")!=null){
//                    actualTrack=stop.get("actualTrack").getAsString();
//                }
//                else{
//                    actualTrack=stop.get("plannedTrack").getAsString();
//                }
//                Station originStation = createOriginStation(originName, plannedDepartureTime, actualDepartureDateTime, plannedTrack, actualTrack);
//                return originStation;
//            }
//
//        });
//
//
//    }

    //TODO Map Trip

    //TODO Map Route

}
