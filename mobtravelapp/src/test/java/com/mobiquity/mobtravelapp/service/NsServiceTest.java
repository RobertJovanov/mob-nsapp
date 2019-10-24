package com.mobiquity.mobtravelapp.service;

import com.mobiquity.mobtravelapp.model.travel.RouteModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class NsServiceTest {
    @Value(("${api.ns.nl.url}"))
    private String nsUri;

    @Value("${api.ns.nl.key}")
    private String nsKey;


    @InjectMocks
    NsService nsService;

    @Mock
    RestTemplate restTemplate;


    @BeforeEach
    void setUp() {
        nsService = new NsService(nsUri, nsKey, restTemplate);
        Mockito.when(restTemplate.exchange(
                ArgumentMatchers.endsWith("v3/trips?fromStation=Amsterdam Zuid&toStation=Duivendrecht&dateTime=2019-10-19T12:30:00"),
                ArgumentMatchers.any(),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(new ResponseEntity<>(getJsonArrayFromTestResource(), HttpStatus.OK));

    }

    @Test
    public void checkIfApiCallIsSuccessful() {

        assertNotNull(nsService.getNsTrips(new RouteModel("Amsterdam Zuid", "Duivendrecht", "2019-10-19T12:30:00")));

    }


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

}