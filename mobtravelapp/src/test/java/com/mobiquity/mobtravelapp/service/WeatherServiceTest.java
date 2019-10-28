package com.mobiquity.mobtravelapp.service;

import com.mobiquity.mobtravelapp.model.travel.Station;
import com.mobiquity.mobtravelapp.model.weather.Weather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertNotNull;


@SpringBootTest
public class WeatherServiceTest {

    Station station = Station.builder().latitude("52.33902").longitude("4.873061").build();

    @Value("${api.darksky.net.url}")
    String weatherUri;

    @Value("${api.darksky.net.key}")
    String weatherKey;

    @InjectMocks
    WeatherService weatherService = new WeatherService();

    @Mock
    RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(weatherService, "uri", weatherUri);
        ReflectionTestUtils.setField(weatherService, "key", weatherKey);
    }

    @Test
    public void checkIfApiCallIsSuccessful() throws Exception {
        String url = String.format("https://api.darksky.net/forecast/%s/52.33902,4.873061,2019-10-15T10:26:00+0200", System.getenv(weatherKey));
        Mockito.when(restTemplate.getForEntity(url, Weather.class)).thenReturn(new ResponseEntity<Weather>(new Weather(), HttpStatus.OK));
            assertNotNull(weatherService.getWeather(station, "2019-10-15T10:26:00+0200"));
    }

}
