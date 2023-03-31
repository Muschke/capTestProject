package com.capgemini.MusscheProject.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PojoRandomQuote {
      @JsonProperty("text")
      private String text;

    public PojoRandomQuote(String text) {
        this.text = text;
    }
    protected PojoRandomQuote(){}

    public String getText() {
        return text;
    }
}
