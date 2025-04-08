package br.com.mddeveloper.API;

import com.fasterxml.jackson.databind.ObjectMapper;
import oauth.signpost.OAuthConsumer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class MediaUploader {
    private static final String mediaUploadApi = "https://upload.twitter.com/1.1/media/upload.json";

    public String uploadMedia(String mediaPath, OAuthConsumer consumer) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            File mediaFile = new File(mediaPath);
            if (!mediaFile.exists()) {
                System.out.println("Arquivo de mídia não encontrado: " + mediaPath);
                return null;
            }

            String boundary = "Boundary-" + System.currentTimeMillis();
            String lineEnd = "\r\n";
            String twoHyphens = "--";

            System.out.println("\n=== Configurando conexão HTTP para upload ===");
            URL url = new URL(mediaUploadApi);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            connection.setDoOutput(true);
            System.out.println("Conexão HTTP configurada");

            System.out.println("\n=== Assinando requisição com OAuth ===");
            consumer.sign(connection);
            System.out.println("Requisição assinada");

            System.out.println("\n=== Enviando dados de mídia ===");
            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write((twoHyphens + boundary + lineEnd).getBytes());
                outputStream.write(("Content-Disposition: form-data; name=\"media\"; filename=\"" + mediaFile.getName() + "\"" + lineEnd).getBytes());
                outputStream.write(("Content-Type: image/png" + lineEnd + lineEnd).getBytes());

                byte[] fileBytes = Files.readAllBytes(mediaFile.toPath());
                outputStream.write(fileBytes);
                outputStream.write(lineEnd.getBytes());

                outputStream.write((twoHyphens + boundary + twoHyphens + lineEnd).getBytes());
            }

            System.out.println("\n=== Obtendo resposta do upload ===");
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
                response.append(line);
            }

            if (responseCode == 200) {
                System.out.println("Upload de mídia concluído: " + response);
                ObjectMapper mapper = new ObjectMapper();
                String mediaId = mapper.readTree(response.toString()).get("media_id_string").asText();
                return mediaId;
            } else {
                System.out.println("Erro ao fazer upload da mídia: " + response + responseCode);
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro durante o upload de mídia: " + e.getMessage());
            return null;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
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