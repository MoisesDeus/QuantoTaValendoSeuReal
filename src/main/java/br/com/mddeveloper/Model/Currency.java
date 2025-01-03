package br.com.mddeveloper.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {
    @JsonProperty("name")
    private String name;
    @JsonProperty("bid")
    private double value;
    @JsonProperty("create_date")
    private String timestamp;


    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
