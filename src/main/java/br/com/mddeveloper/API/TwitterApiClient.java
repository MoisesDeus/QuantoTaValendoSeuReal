package br.com.mddeveloper.API;

import br.com.mddeveloper.DTO.CurrencyDTO;
import br.com.mddeveloper.Model.TweetRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TwitterApiClient {
    private final String bearerToken = "BEARER_TOKEN";

    public void post(CurrencyDTO dto) {
        try {
            String tweet = "Cotaçao: " + dto.getFormattedValue() + " em " + dto.getFormattedDate();
            ObjectMapper mapper = new ObjectMapper();
            String jsonBody = mapper.writeValueAsString(new TweetRequest(tweet));

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.twitter.com/2/tweets"))
                    .header("Authorization", "Bearer " + bearerToken)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201) {
                System.out.println("Tweet feito com sucesso " + response.body());
            } else {
                System.err.println("Erro ao enviar o tweet: " + response.body());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
