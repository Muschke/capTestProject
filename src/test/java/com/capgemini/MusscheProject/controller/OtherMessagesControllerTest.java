package com.capgemini.MusscheProject.controller;

import com.capgemini.MusscheProject.service.interfaces.ApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OtherMessagesControllerTest {
    private OtherMessagesController otherMessagesController;

    @Mock
    ApiService apiService;

    @BeforeEach
    void setUp() {
        otherMessagesController = new OtherMessagesController(apiService);
    }

    @Test
    void getQuoteWorks() {
        otherMessagesController.getQuote();
        verify(apiService).provideRandomQuote();
    }

    @Test
    void getDogFactWorks() {
        otherMessagesController.getDogFact();
        verify(apiService).provideRandomDogFact();
    }

    @Test
    void getweatherWorks() {
        otherMessagesController.getweather("Antwerp");
        verify(apiService).provideWeatherDetails("Antwerp");
    }

}