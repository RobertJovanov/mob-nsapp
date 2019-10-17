package com.mobiquity.mobtravelapp.service;

import com.mobiquity.mobtravelapp.model.travelModel.RouteModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NsServiceTest {
    @Value("${api.ns.nl.url}")
    String nsUri;

    @Value("${api.ns.nl.key}")
    String nsKey;

    @Spy
    NsService nsService;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(nsService, "uri", nsUri);
        ReflectionTestUtils.setField(nsService, "key", nsKey);
    }

    @Test
    public void checkIfApiCallIsSuccessful() throws Exception {
        assertNotNull(nsService.getNsTrips(new RouteModel("Amsterdam Zuid","Duivendrecht","2019-10-19T12:30:00")));

    }


}