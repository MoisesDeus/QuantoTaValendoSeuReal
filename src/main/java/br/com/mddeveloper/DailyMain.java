package br.com.mddeveloper;

import br.com.mddeveloper.API.CurrencyDailyApiClient;
import br.com.mddeveloper.DTO.CurrencyDTO;
import br.com.mddeveloper.Model.Currency;
import br.com.mddeveloper.Service.CurrencyService;
import br.com.mddeveloper.Service.TwitterService;

public class DailyMain {
    public static void main(String[] args) {
        try {
            System.out.println("Iniciando a aplicação (fluxo diário)...");

            CurrencyDailyApiClient dailyApiClient = new CurrencyDailyApiClient();
            CurrencyService currencyService = new CurrencyService();
            TwitterService twitterService = new TwitterService();

            System.out.println("Buscando a cotação atual...");
            Currency currency = dailyApiClient.getCurrency();

            System.out.println("Formatando os dados...");
            CurrencyDTO currencyDTO = currencyService.formatCurrency(currency);

            System.out.println("Postando no X...");
            twitterService.postCurrencyTweet(currencyDTO);

            System.out.println("Processo diário finalizado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao executar a aplicação diária: " + e.getMessage());
            e.printStackTrace();
        }
    }
}