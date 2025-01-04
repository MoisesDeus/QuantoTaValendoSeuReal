package br.com.mddeveloper.DTO;

import br.com.mddeveloper.Model.Tweet;

public class TweetRequestDTO {
    public String text;

    public TweetRequestDTO(Tweet tweet) {
        this.text = tweet.getText();
    }

    public String getText() {
        return text;
    }
}
