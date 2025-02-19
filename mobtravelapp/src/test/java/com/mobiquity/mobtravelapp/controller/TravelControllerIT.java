package com.mobiquity.mobtravelapp.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TravelControllerIT {

    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;


    @Autowired
    TravelController travelController;

    @BeforeEach
    public void setup()  {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetRoutes() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/travelInfo/routes")
                .param("fromStation", "Amsterdam Zuid")
                .param("toStation", "Haarlem")
                .param("dateTime", "2019-10-17T20:00:00")).andDo(print())
                .andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();
        JsonObject jsonObject = new JsonParser().parse(content).getAsJsonObject();
        assertEquals("Amsterdam Zuid", jsonObject.get("origin").getAsString());
    }

}


