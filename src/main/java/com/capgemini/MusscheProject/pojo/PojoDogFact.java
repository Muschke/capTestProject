package com.capgemini.MusscheProject.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PojoDogFact {
    @JsonProperty("facts")
    private ArrayList fact;

    public PojoDogFact(ArrayList fact) {
        this.fact = fact;
    }

    protected PojoDogFact(){}

    public ArrayList getFact() {
        return fact;
    }
}
