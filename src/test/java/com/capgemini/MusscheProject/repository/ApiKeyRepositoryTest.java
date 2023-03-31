package com.capgemini.MusscheProject.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@Sql("/insertApikey.sql")
class ApiKeyRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final static String APIKEYS = "apikeys";
    private ApiKeyRepository apiKeyRepository;
    public ApiKeyRepositoryTest(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }
    @Test
    void findByNameWorks() {
        assertThat(apiKeyRepository.findByName("testapiname").getApiHost()).isEqualTo("testhost");
    }
    @Test
    void findAllWorks(){
        assertThat(apiKeyRepository.findAll()).hasSize(countRowsInTable(APIKEYS));
    }
    @Test
    void findByIdWorks(){
        assertThat(apiKeyRepository.findById(idApikey()))
                .hasValueSatisfying(key -> assertThat(key.getApiKey()).isEqualTo("testapikey"));
    }
    private long idApikey(){
        return jdbcTemplate.queryForObject("SELECT id FROM apikeys WHERE name = 'testapiname'", Long.class);
    }

}