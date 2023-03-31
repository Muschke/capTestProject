package com.capgemini.MusscheProject.api;

import org.hibernate.annotations.Comment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Component
public class RapidApi {
    RestTemplate  restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();


    public ResponseEntity<?> getFamousQuote(String key, String host){
        String URI = "https://"+ host +"/random?category=all&count=2";
        HttpEntity<Object> entity = new HttpEntity<Object>(setHeaders(headers, key, host));
        return restTemplate.exchange(URI, HttpMethod.GET, entity, String.class);
    }

    private HttpHeaders setHeaders(HttpHeaders headers, String key, String host){
        headers.set("X-RapidAPI-Key", key);
        headers.set("X-RapidAPI-Host", host);
        return headers;
    }
}
