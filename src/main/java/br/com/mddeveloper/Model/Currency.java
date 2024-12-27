package br.com.mddeveloper.Model;

public class CurrencyModel {
    private String code;
    private double value;
    private String timestamp;

    public CurrencyModel(String code, double value, String timestamp) {
        this.code = code;
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
