package com.capgemini.MusscheProject.controller;

import com.capgemini.MusscheProject.service.interfaces.ApiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/othermessage")
public class OtherMessagesController {
    private final ApiService apiService;

    public OtherMessagesController(ApiService apiService) {
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

    @GetMapping("/weatherantwerp")
    public String getweather(){
        return apiService.provideWeatherDetails("Antwerp");
    }
}
