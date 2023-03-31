package com.capgemini.MusscheProject.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DogFactApi {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();

    public ResponseEntity<?> getRandomFact(String host){
        String URI = "https://"+ host +"/api/facts";
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);
        return restTemplate.exchange(URI, HttpMethod.GET, entity, String.class);
    }

}
