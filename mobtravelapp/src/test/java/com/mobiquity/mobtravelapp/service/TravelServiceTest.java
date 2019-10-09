package com.mobiquity.mobtravelapp.service;


import com.mobiquity.mobtravelapp.model.RouteModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

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

}
