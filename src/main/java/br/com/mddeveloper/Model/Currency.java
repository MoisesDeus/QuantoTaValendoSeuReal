package br.com.mddeveloper.Model;

public class Currency {
    private String code;
    private double value;
    private String timestamp;

    public Currency(String code, double value, String timestamp) {
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
