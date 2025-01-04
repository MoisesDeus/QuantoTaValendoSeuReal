package br.com.mddeveloper;

import br.com.mddeveloper.API.CurrencyApiClient;
import br.com.mddeveloper.DTO.CurrencyDTO;
import br.com.mddeveloper.Model.Currency;
import br.com.mddeveloper.Service.CurrencyService;
import br.com.mddeveloper.Service.TwitterService;

import javax.sound.midi.Soundbank;

public class Application {
    public static void main(String[] args) {
        try {
            System.out.println("Iniciando a aplicação...");

            CurrencyApiClient apiClient = new CurrencyApiClient();
            CurrencyService currencyService = new CurrencyService();
            TwitterService twitterService = new TwitterService();

            System.out.println("Buscando a cotação atual...");
            Currency currency = apiClient.getCurrency();

            System.out.println("FOrmatando os dados...");
            CurrencyDTO currencyDTO = currencyService.formatCurrency(currency);

            System.out.println("Postando no X...");
            twitterService.postCurrencyTweet(currencyDTO);

            System.out.println("Processo finalizaod com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao executar a aplicação: " + e.getMessage());
            e.printStackTrace();
        }

    }

}