package com.capgemini.MusscheProject.service.interfaces;

import com.capgemini.MusscheProject.entities.ApiKey;

public interface ApiKeyService {
    String saveApiKey(ApiKey apiKey);
    ApiKey getApiKey(String apiKeyname);
}
