package com.capgemini.MusscheProject.service;

import com.capgemini.MusscheProject.api.DogFactApi;
import com.capgemini.MusscheProject.api.RapidApi;
import com.capgemini.MusscheProject.entities.ApiKey;
import com.capgemini.MusscheProject.service.interfaces.ApiKeyService;
import com.capgemini.MusscheProject.service.interfaces.ApiService;
import org.springframework.stereotype.Service;

@Service
public class DefaultApiService implements ApiService {
    private ApiKeyService apiKeyService;
    private RapidApi rapidApi;
    private DogFactApi dogFactApi;

    public DefaultApiService(ApiKeyService apiKeyService,  RapidApi rapidApi, DogFactApi dogFactApi) {
        this.apiKeyService = apiKeyService;
        this.rapidApi = rapidApi;
        this.dogFactApi = dogFactApi;
    }

    @Override
    public String provideRandomQuote() {
        ApiKey rapidkey = apiKeyService.getApiKey("rapidapi");
        return rapidApi.getFamousQuote(rapidkey.getApiKey(), rapidkey.getApiHost()).getBody().toString();
    }

    @Override
    public String provideRandomDogFact(){
        ApiKey dogKey = apiKeyService.getApiKey("dogapi");
        return dogFactApi.getRandomFact(dogKey.getApiHost()).getBody().toString();
    }
}
