package br.com.mddeveloper.API;

import io.github.cdimascio.dotenv.Dotenv;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

public class OAuthConfig {
    private final String consumerKey;
    private final String consumerKeySecret;
    private final String accessToken;
    private final String accessTokenSecret;

    public OAuthConfig() {
        Dotenv dotenv = Dotenv.configure().load();
        this.consumerKey = getRequiredEnvVar("TWITTER_API_KEY", dotenv);
        this.consumerKeySecret = getRequiredEnvVar("TWITTER_API_SECRET", dotenv);
        this.accessToken = getRequiredEnvVar("TWITTER_ACCESS_TOKEN", dotenv);
        this.accessTokenSecret = getRequiredEnvVar("TWITTER_ACCESS_TOKEN_SECRET", dotenv);
    }

    private String getRequiredEnvVar(String name, Dotenv dotenv) {
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

    public OAuthConsumer getOAuthConsumer() {
        OAuthConsumer consumer = new DefaultOAuthConsumer(consumerKey, consumerKeySecret);
        consumer.setTokenWithSecret(accessToken, accessTokenSecret);
        return consumer;
    }

    public void checkConfigs() {
        System.out.println("\n=== Verificando configurações do Twitter ===");
        System.out.println("CONSUMER_KEY: " + (consumerKey != null ? "Configurado" : "NÃO CONFIGURADO"));
        System.out.println("CONSUMER_SECRET: " + (consumerKeySecret != null ? "Configurado" : "NÃO CONFIGURADO"));
        System.out.println("ACCESS_TOKEN: " + (accessToken != null ? "Configurado" : "NÃO CONFIGURADO"));
        System.out.println("ACCESS_TOKEN_SECRET: " + (accessTokenSecret != null ? "Configurado" : "NÃO CONFIGURADO"));
        System.out.println("=========================================\n");
    }
}