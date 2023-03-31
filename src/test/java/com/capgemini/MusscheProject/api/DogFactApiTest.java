package com.capgemini.MusscheProject.api;

import com.capgemini.MusscheProject.pojo.PojoDogFact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DogFactApiTest {
    private DogFactApi dogFactApi;
    private final String HOST = "dog-api.kinduff.com";

    public DogFactApiTest(DogFactApi dogFactApi) {
        this.dogFactApi = dogFactApi;
    }
    @Test
    void getRandomFactCallToApiWorks() {
        ResponseEntity<PojoDogFact> response = dogFactApi.getRandomFact(HOST);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.valueOf(200));
        assertThat(response.getBody()).isInstanceOf(PojoDogFact.class);
    }
}