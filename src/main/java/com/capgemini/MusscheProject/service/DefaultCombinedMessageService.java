package com.capgemini.MusscheProject.service;

import com.capgemini.MusscheProject.entities.Message;
import com.capgemini.MusscheProject.enums.Cities;
import com.capgemini.MusscheProject.service.interfaces.ApiService;
import com.capgemini.MusscheProject.service.interfaces.CombinedMessageService;
import com.capgemini.MusscheProject.service.interfaces.MessageService;
import org.apache.catalina.valves.rewrite.InternalRewriteMap;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DefaultCombinedMessageService implements CombinedMessageService {
    ApiService apiService;
    MessageService messageService;

    public DefaultCombinedMessageService(ApiService apiService, MessageService messageService) {
        this.apiService = apiService;
        this.messageService = messageService;
    }

    public String provideCombinedMessage(String city){
        StringBuilder builder = new StringBuilder();

        if(Arrays.stream(Cities.values()).anyMatch(cities -> cities.toString().equals(city.toLowerCase()))){
            builder.append(messageService.getMessageForCity(city).getMessage());
        } else{
            builder.append(messageService.getMessageForCity("General").getMessage());
        }

        builder.append(", it's currently " + apiService.provideWeatherDetails(city) +"°C.");
        builder.append("\nRemember, " + apiService.provideRandomQuote());
        builder.append(" Even though " + apiService.provideRandomDogFact());

        return builder.toString();
    }
}
