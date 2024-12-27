package br.com.mddeveloper.DTO;

public class CurrencyDTO {
    private String formattedValue;
    private String formattedDate;

    public CurrencyDTO(String formattedValue, String formattedDate) {
        this.formattedValue = formattedValue;
        this.formattedDate = formattedDate;
    }

    public String getFormattedValue() {
        return formattedValue;
    }

    public void setFormattedValue(String formattedValue) {
        this.formattedValue = formattedValue;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    @Override
    public String toString() {
        return "CurrencyDTO{" +
                "formattedValue='" + formattedValue + '\'' +
                ", formattedDate='" + formattedDate + '\'' +
                '}';
    }
}
