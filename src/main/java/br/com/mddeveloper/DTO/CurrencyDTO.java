package br.com.mddeveloper.DTO;

public class CurrencyDTO {
    private String formattedName;
    private String formattedValue;
    private String formattedDate;

    public CurrencyDTO(String formattedName, String formattedValue, String formattedDate) {
        this.formattedName = formattedName;
        this.formattedValue = formattedValue;
        this.formattedDate = formattedDate;
    }

    public String getFormattedName() {
        return formattedName;
    }

    public String getFormattedValue() {
        return formattedValue;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    @Override
    public String toString() {
        return "Cotação:" +
                " " + formattedName + " "
                + formattedValue + " " +
                formattedDate;
    }
}
