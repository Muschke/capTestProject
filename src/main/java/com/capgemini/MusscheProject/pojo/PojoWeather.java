package com.capgemini.MusscheProject.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PojoWeather {
    @JsonProperty("current")
    private PojoCurrent current;

    public PojoWeather(PojoCurrent current) {
        this.current = current;
    }

    protected PojoWeather(){}

    public PojoCurrent getCurrent() {
        return current;
    }
}
