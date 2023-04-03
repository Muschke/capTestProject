package com.capgemini.MusscheProject.controller;

import com.capgemini.MusscheProject.service.interfaces.ApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/othermessage")
public class ApiMessagesController {
    private final ApiService apiService;

    public ApiMessagesController(ApiService apiService) {
        this.apiService = apiService;
    }


    @GetMapping("/randomquote")
    public String getQuote(){
        return apiService.provideRandomQuote();
    }

    @GetMapping("/randomdogfact")
    public String getDogFact(){
        return apiService.provideRandomDogFact();
    }

    @GetMapping("/weatherantwerp/{city}")
    public int getweather(@PathVariable String city){
        return apiService.provideWeatherDetails(city);
    }
}
