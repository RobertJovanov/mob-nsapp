package com.mobiquity.mobtravelapp.service;
import com.mobiquity.mobtravelapp.model.weatherModel.Weather;
import com.mobiquity.mobtravelapp.model.travelModel.Station;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherServiceTest {

    Station station = Station.builder().latitude("52.33902").longitude("4.873061").build();

    @Value("${api.darksky.net.url}")
    String weatherUri;

    @Value("${api.darksky.net.key}")
    String weatherKey;

    @InjectMocks
    WeatherService weatherService;

    @Mock
    RestTemplate restTemplate;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(weatherService, "uri", weatherUri);
        ReflectionTestUtils.setField(weatherService, "key", weatherKey);
        Mockito.when(restTemplate.getForEntity("https://api.darksky.net/forecast/b5548ad13c478c1abc522db68b7761cb/52.33902,4.873061,2019-10-15T10:26:00+0200", com.mobiquity.mobtravelapp.model.weatherModel.Weather.class))
                .thenReturn(new ResponseEntity<Weather>(new Weather(), HttpStatus.OK));
    }

    @Test
    public void checkIfApiCallIsSuccessful() throws Exception {
        assertNotNull(weatherService.getWeather(station, "2019-10-15T10:26:00+0200"));
    }


}
