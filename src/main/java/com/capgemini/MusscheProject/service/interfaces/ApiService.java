package com.capgemini.MusscheProject.service.interfaces;

public interface ApiService {
    String provideRandomQuote();
    String provideRandomDogFact();
    int provideWeatherDetails(String city);

}
