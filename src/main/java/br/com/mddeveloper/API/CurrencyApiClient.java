package br.com.mddeveloper.API;

import br.com.mddeveloper.Model.Currency;
import br.com.mddeveloper.Model.CurrencyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyApiClient {
    public Currency getCurrency() {
        URI url = URI.create(("https://economia.awesomeapi.com.br/last/USD-BRL"));

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(url).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Json recebido" + response.body());

            ObjectMapper mapper = new ObjectMapper();
            CurrencyResponse currencyResponse =  mapper.readValue(response.body(), CurrencyResponse.class);
            return currencyResponse.getUsdbrl();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter dados da API: " + e);
        }
    }
}
