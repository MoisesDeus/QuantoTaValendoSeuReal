package br.com.mddeveloper.Service;

import br.com.mddeveloper.API.TwitterApiClient;
import br.com.mddeveloper.DTO.CurrencyDTO;
import br.com.mddeveloper.Model.Tweet;

import java.util.Currency;

public class TwitterService {
    private final TwitterApiClient apiClient;

    public TwitterService() {
        this.apiClient = new TwitterApiClient();
    }

    public void postCurrencyTweet(CurrencyDTO dto) {
        String tweetText = "Cotação: " + dto.getFormattedValue() + "em " + dto.getFormattedDate();
        Tweet tweet = new Tweet(tweetText);
        apiClient.post(tweet);
    }
}
