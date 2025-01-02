package br.com.mddeveloper;

import br.com.mddeveloper.API.CurrencyApiClient;
import br.com.mddeveloper.DTO.CurrencyDTO;
import br.com.mddeveloper.Model.Currency;
import br.com.mddeveloper.Service.CurrencyService;

public class Main {
    public static void main(String[] args) {
        CurrencyApiClient currencyApiClient = new CurrencyApiClient();

        CurrencyService currencyService = new CurrencyService();

        currencyService.formatCurrency(currencyApiClient.getCurrency());

//        System.out.println(currencyApiClient.getCurrency());

//        System.out.println("Dolár: " + currency.getUSD());
//        System.out.println("Real: " + currency.getBRL());
//        System.out.println("Código: " + currency.getValue());
//        System.out.println("Data: " + currency.getTimestamp());

    }

}