package com.mobiquity.mobtravelapp.service;

import com.mobiquity.mobtravelapp.model.travelModel.RouteModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NsServiceTest {
    @Value("${api.ns.nl.url}")
    String nsUri;

    @Value("${api.ns.nl.key}")
    String nsKey;

    @InjectMocks
    NsService nsService;

    @Mock
    RestTemplate restTemplate;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(nsService, "uri", nsUri);
        ReflectionTestUtils.setField(nsService, "key", nsKey);
        Mockito.when(restTemplate.exchange(
                ArgumentMatchers.endsWith("v3/trips?fromStation=Amsterdam Zuid&toStation=Duivendrecht&dateTime=2019-10-19T12:30:00"),
                ArgumentMatchers.any(),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(new ResponseEntity<String>(getJsonArrayFromTestResource(), HttpStatus.OK));
    }

    @Test
    public void checkIfApiCallIsSuccessful() throws Exception {
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