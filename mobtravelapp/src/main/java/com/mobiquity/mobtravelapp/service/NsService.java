package com.mobiquity.mobtravelapp.service;

import com.mobiquity.mobtravelapp.exception.IncorrectFormatException;
import com.mobiquity.mobtravelapp.model.travel.RouteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

@Service
public class NsService {
    @Value("${api.ns.nl.url}")
    private String uri;

    @Value("${api.ns.nl.key}")
    private String key;

    @Autowired
    private RestTemplate restTemplate;

    public String getNsTrips(RouteModel routeModelAfterReformat) throws IncorrectFormatException{
        String url = MessageFormat.format(uri, "fromStation=" + routeModelAfterReformat.getFromStation(),
                "toStation=" + routeModelAfterReformat.getToStation(), "dateTime=" + routeModelAfterReformat.getDateTime());


        //RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Ocp-Apim-Subscription-Key", System.getenv(this.key));

        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
        ResponseEntity<String> result;
        try {
            result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        } catch (RestClientException e) {
            throw new IncorrectFormatException("Bad Request");

        }
        return result.getBody();
    }
}
