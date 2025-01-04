package br.com.mddeveloper.API;

import br.com.mddeveloper.DTO.TweetRequestDTO;
import br.com.mddeveloper.Model.Tweet;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TwitterApiClient {
    private static final Dotenv dotenv = Dotenv.configure().load();

//    private static final String bearerToken = "BEARER_TOKEN";
    private static final String consumerKey = getRequiredEnvVar("TWITTER_API_KEY");
    private static final String consumerKeySecret = getRequiredEnvVar("TWITTER_API_SECRET");
    private static final String accessToken = getRequiredEnvVar("TWITTER_ACCESS_TOKEN");
    private static final String accessTokenSecret = getRequiredEnvVar("TWITTER_ACCESS_TOKEN_SECRET");

    private static String getRequiredEnvVar(String name) {
        String value = dotenv.get(name);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalStateException(
                    "\nVariável de ambiente '" + name + "' não encontrada no arquivo .env\n" +
                            "Certifique-se de que:\n" +
                            "1. O arquivo .env existe na raiz do projeto\n" +
                            "2. A variável " + name + " está definida corretamente no arquivo\n" +
                            "3. O arquivo .env está no formato correto (CHAVE=VALOR)\n"
            );
        }
        return value;
    }

    public void verificarConfiguracoes() {
        System.out.println("\n=== Verificando configurações do Twitter ===");
        System.out.println("CONSUMER_KEY: " + (consumerKey != null ? "Configurado" : "NÃO CONFIGURADO"));
        System.out.println("CONSUMER_SECRET: " + (consumerKeySecret != null ? "Configurado" : "NÃO CONFIGURADO"));
        System.out.println("ACCESS_TOKEN: " + (accessToken != null ? "Configurado" : "NÃO CONFIGURADO"));
        System.out.println("ACCESS_TOKEN_SECRET: " + (accessTokenSecret != null ? "Configurado" : "NÃO CONFIGURADO"));
        System.out.println("=========================================\n");
    }

    private static final String twitterApi = "https://api.twitter.com/2/tweets";


    public void post(Tweet tweet) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        OutputStream outputStream= null;

        try {
            verificarConfiguracoes();

            TweetRequestDTO tweetRequest = new TweetRequestDTO(tweet);
            ObjectMapper mapper = new ObjectMapper();
            String jsonBody = mapper.writeValueAsString(tweetRequest);

            System.out.println("\n=== Configurando OAuth ===");
            OAuthConsumer consumer = new DefaultOAuthConsumer(
                    consumerKey,
                    consumerKeySecret
            );
            consumer.setTokenWithSecret(accessToken, accessTokenSecret);
            System.out.println("OAuth Consumer configurado");

            System.out.println("\n=== Configurando conexão HTTP ===");
            URL url = new URL(twitterApi);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            System.out.println("Conexão HTTP configurada");

//            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
//                    .uri(URI.create(twitterApi))
//                    .header("Content-Type", "application/json")
//                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody));

            System.out.println("\n=== Assinando requisição com OAuth ===");
            consumer.sign(connection);
            System.out.println("Requisição assinada");

            System.out.println("\nHeaders da requisição:");
            connection.getRequestProperties().forEach((key, value) ->
                    System.out.println(key + ": " + value));

            System.out.println("\n=== Enviando dados ===");
            outputStream = connection.getOutputStream();
            byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
            outputStream.close();
            System.out.println("Dados enviados");

            System.out.println("\n=== Obtendo resposta ===");
            int responseCode = connection.getResponseCode();
            System.out.println("Código de resposta: " + responseCode);

            StringBuilder response = new StringBuilder();

//            Map<String, String> headers = new HashMap<>();
//            consumer.sign(requestBuilder.build());
//            headers.forEach((key, value) -> requestBuilder.header(key, value));
//
//            HttpClient client = HttpClient.newHttpClient();
//            HttpResponse<String> response = client.
//                    send(requestBuilder.build(), HttpResponse.BodyHandlers.ofString());

//            if (response.statusCode() == 201) {
//                System.out.println("Tweet feito com sucesso " + response.body());
//            } else {
//                System.err.println("Erro ao enviar o tweet: " + response.body());
//            }

            if (responseCode >= 400) {
                reader = new BufferedReader(
                        new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8)
                );
            } else {
                reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8)
                );
            }

            String line;
            while ((line = reader.readLine()) !=null) {
                response.append(line);
                response.append("\n");
            }

            if (responseCode == 201) {
                System.out.println("Tweet postado com sucesso: " + response);
            } else {
                System.out.println("erro ao postar: " + response + responseCode);
            }

        } catch (Exception e) {
            System.out.println("Erro durante a execução: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (Exception e) {
                System.out.println("Erro ao fechar os recursos: " + e.getMessage());
            }
        }
    }
}
