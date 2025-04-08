package br.com.mddeveloper.API;

import br.com.mddeveloper.DTO.TweetRequestDTO;
import br.com.mddeveloper.Model.Tweet;
import br.com.mddeveloper.Util.HttpRequestSender;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TwitterApiClient {
    private static final String twitterApi = "https://api.twitter.com/2/tweets";

    private final OAuthConfig oauthConfig;
    private final HttpRequestSender requestSender;
    private final MediaUploader mediaUploader;

    public TwitterApiClient() {
        this.oauthConfig = new OAuthConfig();
        this.requestSender = new HttpRequestSender();
        this.mediaUploader = new MediaUploader();
    }

    public void post(Tweet tweet) {
        oauthConfig.checkConfigs();

        try {
            TweetRequestDTO tweetRequest = new TweetRequestDTO(tweet);
            ObjectMapper mapper = new ObjectMapper();
            String jsonBody = mapper.writeValueAsString(tweetRequest);

            System.out.println("\n=== Configurando OAuth ===");
            System.out.println("OAuth Consumer configurado");

            String response = requestSender.sendPostRequest(twitterApi, jsonBody, oauthConfig.getOAuthConsumer());
            int responseCode = Integer.parseInt(response.split(" ")[0]);
            String responseBody = response.substring(response.indexOf(" ") + 1);

            if (responseCode == 201) {
                System.out.println("Tweet postado com sucesso: " + responseBody);
            } else {
                System.out.println("Erro ao postar: " + responseBody + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Erro ao postar tweet: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void postWithMedia(Tweet tweet, String mediaPath) {
        oauthConfig.checkConfigs();

        String mediaId = mediaUploader.uploadMedia(mediaPath, oauthConfig.getOAuthConsumer());
        if (mediaId == null) {
            throw new RuntimeException("Falha ao fazer upload da imagem.");
        }

        try {
            String jsonBody = String.format(
                    "{\"text\": \"%s\", \"media\": {\"media_ids\": [\"%s\"]}}",
                    tweet.getText(),
                    mediaId
            );

            System.out.println("\n=== Configurando OAuth ===");
            System.out.println("OAuth Consumer configurado");

            String response = requestSender.sendPostRequest(twitterApi, jsonBody, oauthConfig.getOAuthConsumer());
            int responseCode = Integer.parseInt(response.split(" ")[0]);
            String responseBody = response.substring(response.indexOf(" ") + 1);

            if (responseCode == 201) {
                System.out.println("Tweet com gráfico postado com sucesso: " + responseBody);
            } else {
                System.out.println("Erro ao postar tweet com gráfico: " + responseBody + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Erro ao postar tweet com gráfico: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}