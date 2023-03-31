package com.capgemini.MusscheProject.api;

import com.capgemini.MusscheProject.entities.ApiKey;
import com.capgemini.MusscheProject.pojo.PojoDogFact;
import com.capgemini.MusscheProject.pojo.PojoRandomQuote;
import com.capgemini.MusscheProject.service.interfaces.ApiKeyService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RapidApiTest {
    private RapidApi rapidApi;
    private ApiKeyService apiKeyService;

    public RapidApiTest(RapidApi rapidApi, ApiKeyService apiKeyService) {
        this.rapidApi = rapidApi;
        this.apiKeyService = apiKeyService;
    }

    @Test
    void getFamousQuoteCallToApiWorks() {
        ApiKey key  = apiKeyService.getApiKey("rapidapi");
        ResponseEntity<PojoRandomQuote[]> response = rapidApi.getFamousQuote(key.getApiKey(), key.getApiHost());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.valueOf(200));
        assertThat(response.getBody()).isInstanceOf(PojoRandomQuote[].class);
        assertThat(response.getBody()).hasSize(2);
    }

}