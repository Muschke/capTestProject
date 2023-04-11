package com.capgemini.MusscheProject.controller.junit;

import com.capgemini.MusscheProject.controller.ApiMessagesController;
import com.capgemini.MusscheProject.service.interfaces.ApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ApiMessagesControllerTest {
    private ApiMessagesController apiMessagesController;

    @Mock
    ApiService apiService;

    @BeforeEach
    void setUp() {
        apiMessagesController = new ApiMessagesController(apiService);
    }

    @Test
    void getQuoteWorks() {
        apiMessagesController.getQuote();
        verify(apiService).provideRandomQuote();
    }

    @Test
    void getDogFactWorks() {
        apiMessagesController.getDogFact();
        verify(apiService).provideRandomDogFact();
    }

    @Test
    void getweatherWorks() {
        apiMessagesController.getweather("Antwerp");
        verify(apiService).provideWeatherDetails("Antwerp");
    }

}