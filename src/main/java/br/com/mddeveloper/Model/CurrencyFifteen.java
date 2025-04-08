package br.com.mddeveloper.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyFifteen {
    @JsonProperty("bid")
    private double value;
    @JsonProperty("timestamp")
    private String timestamp;

    public double getValue() {
        return value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "CurrencyFifteen{" +
                "value=" + value +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
