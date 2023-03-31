package com.capgemini.MusscheProject.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PojoCurrent {
    @JsonProperty("temperature")
    private int temperature;

    public int getTemperature() {
        return temperature;
    }
}
