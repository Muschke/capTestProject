package com.capgemini.MusscheProject.api;

import com.capgemini.MusscheProject.entities.ApiKey;
import com.capgemini.MusscheProject.pojo.PojoRandomQuote;
import com.capgemini.MusscheProject.pojo.PojoWeather;
import com.capgemini.MusscheProject.service.interfaces.ApiKeyService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class WeatherApiTest {
    private WeatherApi weatherApi;
    private ApiKeyService apiKeyService;

    public WeatherApiTest(WeatherApi weatherApi, ApiKeyService apiKeyService) {
        this.weatherApi = weatherApi;
        this.apiKeyService = apiKeyService;
    }

    @Test
    void getWeatherOfSpecificCity() {
        ApiKey key  = apiKeyService.getApiKey("weatherapi");
        ResponseEntity<PojoWeather> response = weatherApi.getWeatherOfSpecificCity(key.getApiKey(), key.getApiHost(), "Antwerp");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.valueOf(200));
        assertThat(response.getBody()).isInstanceOf(PojoWeather.class);

    }
}