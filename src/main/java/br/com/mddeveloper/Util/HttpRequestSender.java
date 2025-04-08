package br.com.mddeveloper.Util;

import oauth.signpost.OAuthConsumer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpRequestSender {
    public String sendPostRequest(String url, String jsonBody, OAuthConsumer consumer) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        OutputStream outputStream = null;

        try {
            System.out.println("\n=== Configurando conexão HTTP ===");
            URL apiUrl = new URL(url);
            connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            System.out.println("Conexão HTTP configurada");

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
            while ((line = reader.readLine()) != null) {
                response.append(line).append("\n");
            }

            return responseCode + " " + response.toString();

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
