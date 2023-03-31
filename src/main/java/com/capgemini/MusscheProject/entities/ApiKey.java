package com.capgemini.MusscheProject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="apikeys")
public class ApiKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String apiHost;
    @NotBlank
    private String apiKey;
    @NotNull
    private int renewals;

    public ApiKey(String name, String apiKey, String apiHost) {
        this.name = name;
        this.apiKey = apiKey;
        this.apiHost = apiHost;
        this.renewals = 0;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    protected ApiKey(){};


    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public void setRenewals(int renewals) {
        this.renewals = renewals;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getApiKey() {
        return apiKey;
    }
    public String getApiHost() {
        return apiHost;
    }
    public int getRenewals() {
        return renewals;
    }
}
