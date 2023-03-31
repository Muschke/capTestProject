package com.capgemini.MusscheProject.service;

import com.capgemini.MusscheProject.entities.ApiKey;
import com.capgemini.MusscheProject.repository.ApiKeyRepository;
import com.capgemini.MusscheProject.service.interfaces.ApiKeyService;
import org.springframework.stereotype.Service;

@Service
public class DefaultApiKeyService implements ApiKeyService {
    ApiKeyRepository apiKeyRepository;

    public DefaultApiKeyService(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public String saveApiKey(ApiKey apiKey) {
        apiKeyRepository.save(apiKey);
        return "key saved succesfully";
    }

    @Override
    public ApiKey getApiKey(String apiKeyname) {
        return apiKeyRepository.findByName(apiKeyname);
    }
}
