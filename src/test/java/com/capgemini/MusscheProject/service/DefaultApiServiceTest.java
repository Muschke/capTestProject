package com.capgemini.MusscheProject.service;

import com.capgemini.MusscheProject.api.DogFactApi;
import com.capgemini.MusscheProject.api.RapidApi;
import com.capgemini.MusscheProject.api.WeatherApi;
import com.capgemini.MusscheProject.entities.ApiKey;
import com.capgemini.MusscheProject.pojo.PojoCurrent;
import com.capgemini.MusscheProject.pojo.PojoDogFact;
import com.capgemini.MusscheProject.pojo.PojoRandomQuote;
import com.capgemini.MusscheProject.pojo.PojoWeather;
import com.capgemini.MusscheProject.service.interfaces.ApiKeyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DefaultApiServiceTest {
    private DefaultApiService apiService;
    private ApiKey testApiKey;

    @Mock
    ApiKeyService apiKeyService;
    @Mock
    DogFactApi dogFactApi;
    @Mock
    RapidApi rapidApi;
    @Mock
    WeatherApi weatherApi;

    @BeforeEach
    void setUp() {
        apiService = new DefaultApiService(apiKeyService, rapidApi, dogFactApi, weatherApi);
        testApiKey = new ApiKey("testname", "testapikey", "testapihost");
    }

    @Test
    void provideRandomQuoteWorks() {
        ResponseEntity<PojoRandomQuote[]> mockedResponse = generateMockResponsePojoRandomQuote();

        when(apiKeyService.getApiKey("rapidapi")).thenReturn(testApiKey);
        when(rapidApi.getFamousQuote(testApiKey.getApiKey(), testApiKey.getApiHost())).thenReturn(mockedResponse);

        String response = apiService.provideRandomQuote();

        verify(apiKeyService).getApiKey("rapidapi");
        verify(rapidApi).getFamousQuote(testApiKey.getApiKey(), testApiKey.getApiHost());
        assertThat(response).isEqualTo("pojoRandomQuoteOne");
    }
    @Test
    void provideRandomDogFactWorks() {
        ResponseEntity<PojoDogFact> mockedResponse = generateMockResponsePojoDogFact();

        when(apiKeyService.getApiKey("dogapi")).thenReturn(testApiKey);
        when(dogFactApi.getRandomFact("testapihost")).thenReturn(mockedResponse);

        String response = apiService.provideRandomDogFact();

        verify(apiKeyService).getApiKey("dogapi");
        verify(dogFactApi).getRandomFact("testapihost");
        assertThat(response).isEqualTo("randTestFactOne");

    }
    @Test
    void provideWeatherDetailsWorks() {
        ResponseEntity<PojoWeather> mockedResponse = generateMockResponsePojoWeather();

        when(apiKeyService.getApiKey("weatherapi")).thenReturn(testApiKey);
        when(weatherApi.getWeatherOfSpecificCity(testApiKey.getApiKey(), testApiKey.getApiHost(),"Antwerp")).thenReturn(mockedResponse);

        int response = apiService.provideWeatherDetails("Antwerp");

        verify(apiKeyService).getApiKey("weatherapi");
        verify(weatherApi).getWeatherOfSpecificCity(testApiKey.getApiKey(), testApiKey.getApiHost(),"Antwerp");
        assertThat(response).isEqualTo(0);
    }
    private ResponseEntity<PojoRandomQuote[]> generateMockResponsePojoRandomQuote(){
        PojoRandomQuote[] pojoRandomQuotes = new PojoRandomQuote[2];
        pojoRandomQuotes[0] = new PojoRandomQuote("pojoRandomQuoteOne");
        pojoRandomQuotes[1] = new PojoRandomQuote("pojoRandomQuoteTwo");
        return new ResponseEntity<PojoRandomQuote[]>(pojoRandomQuotes, HttpStatus.ACCEPTED);
    }
    private ResponseEntity<PojoDogFact> generateMockResponsePojoDogFact(){
        ArrayList<String> randomfacts = new ArrayList<>();
        randomfacts.add(new String("randTestFactOne"));
        randomfacts.add(new String("randTestFactTwo"));
        PojoDogFact pojoDogFact = new PojoDogFact(randomfacts);
        return new ResponseEntity<PojoDogFact>(pojoDogFact, HttpStatus.ACCEPTED);
    }
    private ResponseEntity<PojoWeather> generateMockResponsePojoWeather(){
        PojoCurrent pojoCurrent = new PojoCurrent(0);
        PojoWeather pojoWeather = new PojoWeather(pojoCurrent);
        return new ResponseEntity<PojoWeather>(pojoWeather, HttpStatus.ACCEPTED);
    }
}