package br.com.mddeveloper;

import br.com.mddeveloper.API.CurrencyFifteenApiClient;
import br.com.mddeveloper.DTO.TweetRequestDTO;
import br.com.mddeveloper.Model.CurrencyFifteen;
import br.com.mddeveloper.Service.ChartService;
import br.com.mddeveloper.Service.TwitterService;

import java.util.List;

public class ChartMain {
    public static void main(String[] args) {
        try {
            System.out.println("Iniciando a aplicação (fluxo do gráfico)...");

            CurrencyFifteenApiClient fifteenApiClient = new CurrencyFifteenApiClient();
            ChartService chartGenerator = new ChartService();
            TwitterService twitterService = new TwitterService();

            System.out.println("Buscando cotações dos últimos 15 dias...");
            List<CurrencyFifteen> cotacoes = fifteenApiClient.fetchFifteenCurrency();
            if (cotacoes != null) {
                System.out.println("Gerando o gráfico...");
                chartGenerator.generateChart(cotacoes);

                System.out.println("Postando o gráfico no X...");
                TweetRequestDTO tweetDTO = new TweetRequestDTO("Cotação USD/BRL - Últimos 15 Dias");
                twitterService.postChartTweet(tweetDTO);
            } else {
                System.out.println("Falha ao buscar cotações dos últimos 15 dias.");
            }

            System.out.println("Processo do gráfico finalizado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao executar a aplicação do gráfico: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
