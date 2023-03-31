package com.capgemini.MusscheProject.service;

import com.capgemini.MusscheProject.api.DogFactApi;
import com.capgemini.MusscheProject.api.RapidApi;
import com.capgemini.MusscheProject.api.WeatherApi;
import com.capgemini.MusscheProject.entities.ApiKey;
import com.capgemini.MusscheProject.pojo.PojoRandomQuote;
import com.capgemini.MusscheProject.service.interfaces.ApiKeyService;
import com.capgemini.MusscheProject.service.interfaces.ApiService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DefaultApiService implements ApiService {
    private ApiKeyService apiKeyService;
    private RapidApi rapidApi;
    private DogFactApi dogFactApi;
    private WeatherApi weatherApi;

    public DefaultApiService(ApiKeyService apiKeyService,  RapidApi rapidApi, DogFactApi dogFactApi, WeatherApi weatherApi) {
        this.apiKeyService = apiKeyService;
        this.rapidApi = rapidApi;
        this.dogFactApi = dogFactApi;
        this.weatherApi = weatherApi;
    }

    @Override
    public String provideRandomQuote() {
        ApiKey rapidkey = apiKeyService.getApiKey("rapidapi");
        return Arrays.stream(rapidApi.getFamousQuote(rapidkey.getApiKey(), rapidkey.getApiHost()).getBody()).findFirst().get().getText();
    }

    @Override
    public String provideRandomDogFact(){
        ApiKey dogKey = apiKeyService.getApiKey("dogapi");
        return dogFactApi.getRandomFact(dogKey.getApiHost()).getBody().getFact().get(0).toString();
    }

    @Override
    public int provideWeatherDetails(String city){
        ApiKey weatherapiKey = apiKeyService.getApiKey("weatherapi");
        return weatherApi.getWeatherOfSpecificCity(weatherapiKey.getApiKey(), weatherapiKey.getApiHost(), city).getBody().getCurrent().getTemperature();
    }
}
