package br.com.mddeveloper.Model;

public class TweetRequest {
    private final String text;

    public TweetRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
