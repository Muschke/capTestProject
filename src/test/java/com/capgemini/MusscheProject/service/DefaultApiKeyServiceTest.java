package com.capgemini.MusscheProject.service;

import com.capgemini.MusscheProject.entities.ApiKey;
import com.capgemini.MusscheProject.repository.ApiKeyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DefaultApiKeyServiceTest {
    private DefaultApiKeyService apiKeyService;
    private ApiKey apiKey;

    @Mock
    ApiKeyRepository apiKeyRepository;

    @BeforeEach
    void setUp() {
        apiKeyService = new DefaultApiKeyService(apiKeyRepository);
        apiKey = new ApiKey("testNameService", "testKeyService", "testKeyHost");
    }

    @Test
    void saveApiKeyWorks() {
        String response = apiKeyService.saveApiKey(apiKey);
        verify(apiKeyRepository).save(apiKey);
        assertThat(response).isEqualTo("key saved succesfully");
    }

    @Test
    void getApiKeyWorks() {
        apiKeyService.getApiKey("randdomname");
        verify(apiKeyRepository).findByName("randdomname");
    }
}