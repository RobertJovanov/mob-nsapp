package com.mobiquity.mobtravelapp.service;

import com.mobiquity.mobtravelapp.model.travelModel.Station;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherServiceTest {

    Station station=Station.builder().latitude("52.33902").longitude("4.873061").build();
    @Spy
    WeatherService weatherService;


    @Before
    public void setUp() {
        ReflectionTestUtils.setField(weatherService, "uri", "https://api.darksky.net/forecast/{0}/{1},{2},{3}");
        ReflectionTestUtils.setField(weatherService, "key", System.getenv("DARKSKYAPIKEY"));
    }
    @Test
    public void checkIfApiCallIsSuccessful(){
        weatherService.getWeather(station,"2019-10-15T10:26:00+0200");
    }

}
