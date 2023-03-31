package com.capgemini.MusscheProject.service;

import com.capgemini.MusscheProject.entities.Message;
import com.capgemini.MusscheProject.enums.Cities;
import com.capgemini.MusscheProject.service.interfaces.ApiService;
import com.capgemini.MusscheProject.service.interfaces.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultCombinedMessageServiceTest {
    private DefaultCombinedMessageService combinedMessageService;
    private Message message;
    private Message otherMessage;

    @Mock
    ApiService apiService;
    @Mock
    MessageService messageService;

    @BeforeEach
    void setUp() {
        combinedMessageService = new DefaultCombinedMessageService(apiService, messageService);
        message = new Message("Antwerp", "Goeiemorge Antwaarpe");
        otherMessage = new Message("General", "Hello World");
    }

    @Test
    void provideCombinedMessageWithCityInEnumWorks() {
        when(messageService.getMessageForCity("Antwerp")).thenReturn(message);
        when(apiService.provideWeatherDetails("Antwerp")).thenReturn(15);
        when(apiService.provideRandomQuote()).thenReturn("randomQuote");
        when(apiService.provideRandomDogFact()).thenReturn("randomFact");

        String response = combinedMessageService.provideCombinedMessage("Antwerp");

        verify(messageService).getMessageForCity("Antwerp");
        verify(apiService).provideWeatherDetails("Antwerp");
        verify(apiService).provideRandomQuote();
        verify(apiService).provideRandomDogFact();

        assertThat(response).isEqualTo("Goeiemorge Antwaarpe, it's currently 15°C.\nRemember, randomQuote Even though randomFact");
    }

    @Test
    void provideCombinedMessageWithOtherCity() {
        when(messageService.getMessageForCity("General")).thenReturn(otherMessage);
        when(apiService.provideWeatherDetails("Bangkok")).thenReturn(25);
        when(apiService.provideRandomQuote()).thenReturn("randomQuote");
        when(apiService.provideRandomDogFact()).thenReturn("randomFact");

        String response = combinedMessageService.provideCombinedMessage("Bangkok");

        verify(messageService).getMessageForCity("General");
        verify(apiService).provideWeatherDetails("Bangkok");
        verify(apiService).provideRandomQuote();
        verify(apiService).provideRandomDogFact();

        assertThat(response).isEqualTo("Hello World, it's currently 25°C.\nRemember, randomQuote Even though randomFact");
    }

}