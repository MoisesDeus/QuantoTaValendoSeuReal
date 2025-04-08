package br.com.mddeveloper.Service;

import br.com.mddeveloper.API.TwitterApiClient;
import br.com.mddeveloper.DTO.CurrencyDTO;
import br.com.mddeveloper.DTO.TweetRequestDTO;
import br.com.mddeveloper.Model.Tweet;

public class TwitterService {
    private final TwitterApiClient apiClient;

    public TwitterService() {
        this.apiClient = new TwitterApiClient();
    }

    public void postCurrencyTweet(CurrencyDTO dto) {
        String tweetText = dto.getFormattedName() + dto.getFormattedValue() + dto.getFormattedDate();
        Tweet tweet = new Tweet(tweetText);
        apiClient.post(tweet);
    }

    public void postChartTweet(TweetRequestDTO dto) {
        Tweet tweet = new Tweet(dto.getText());
        apiClient.postWithMedia(tweet, "chart.png");
    }
}
