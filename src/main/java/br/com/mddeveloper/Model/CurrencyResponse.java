package br.com.mddeveloper.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyResponse {
    @JsonProperty("USDBRL")
    private Currency usdbrl;

    public Currency getUsdbrl() {
        return usdbrl;
    }

    public void setUsdbrl(Currency usdbrl) {
        this.usdbrl = usdbrl;
    }
}
