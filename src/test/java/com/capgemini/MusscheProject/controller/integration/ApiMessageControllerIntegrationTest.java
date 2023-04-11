package com.capgemini.MusscheProject.controller.integration;

import com.capgemini.MusscheProject.payload.IncomingMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiMessageControllerIntegrationTest {
    private final static String URI = "/othermessage/";
    private final MockMvc mockMvc;


    public ApiMessageControllerIntegrationTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void testGetQuoteWorks() throws Exception{
        mockMvc.perform(get(URI+"randomquote"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    void testGetDogFact() throws Exception{
        mockMvc.perform(get(URI+"randomdogfact"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    void testGetweather() throws Exception{
        mockMvc.perform(get(URI+"weather/Antwerpen"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }
}
