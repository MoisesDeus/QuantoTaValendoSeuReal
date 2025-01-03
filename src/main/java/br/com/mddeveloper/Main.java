package br.com.mddeveloper;

import br.com.mddeveloper.API.CurrencyApiClient;
import br.com.mddeveloper.API.TwitterApiClient;
import br.com.mddeveloper.DTO.CurrencyDTO;
import br.com.mddeveloper.Model.Currency;
import br.com.mddeveloper.Service.CurrencyService;

public class Main {
    public static void main(String[] args) {
        CurrencyApiClient currencyApiClient = new CurrencyApiClient();
        Currency currency = currencyApiClient.getCurrency();
        CurrencyService service = new CurrencyService();

        CurrencyDTO dto = service.formatCurrency(currency);

        TwitterApiClient tweet = new TwitterApiClient();
        tweet.post(dto);

    }

}