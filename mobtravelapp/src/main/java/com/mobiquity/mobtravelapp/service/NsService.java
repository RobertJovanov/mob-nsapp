package com.mobiquity.mobtravelapp.service;

import com.mobiquity.mobtravelapp.exception.IncorrectFormatException;
import com.mobiquity.mobtravelapp.model.travel.RouteModel;
import lombok.AllArgsConstructor;
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

    private String uri;

    private String key;

    private RestTemplate restTemplate;

    public NsService(@Value("${api.ns.nl.url}") String uri, @Value("${api.ns.nl.key}") String key,RestTemplate restTemplate){
       this.uri=uri;
       this.key=key;
       this.restTemplate=restTemplate;
    }

    /**
     * Extracts all trips for given station names and datetime
     * via a call to Ns.nl api
     *
     * @param routeModelAfterReformat object having station names and date time
     * @return String object
     */

    public String getNsTrips(RouteModel routeModelAfterReformat) {

        String url = MessageFormat.format(uri, "fromStation=" + routeModelAfterReformat.getFromStation(),
                "toStation=" + routeModelAfterReformat.getToStation(), "dateTime=" + routeModelAfterReformat.getDateTime());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Ocp-Apim-Subscription-Key", System.getenv(this.key));

        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
        ResponseEntity<String> result;
        try {
            result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        } catch (RestClientException e) {
            throw new IncorrectFormatException("Bad Request");
        } catch(NullPointerException e){
            System.out.println("uri and key must be fetched properly");
            throw new NullPointerException();
        }
        return result.getBody();
    }
}
