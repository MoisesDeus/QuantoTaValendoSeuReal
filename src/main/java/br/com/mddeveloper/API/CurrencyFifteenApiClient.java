package br.com.mddeveloper.API;

import br.com.mddeveloper.Model.CurrencyFifteen;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class CurrencyFifteenApiClient {
    public List<CurrencyFifteen> fetchFifteenCurrency() {
        URI url = URI.create(("https://economia.awesomeapi.com.br/json/daily/USD-BRL/15"));
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(url).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Json recebido" + response.body());

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.body(), mapper.getTypeFactory().constructCollectionType(List.class, CurrencyFifteen.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
