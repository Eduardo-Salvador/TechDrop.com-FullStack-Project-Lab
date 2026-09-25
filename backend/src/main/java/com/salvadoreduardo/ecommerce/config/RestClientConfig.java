package com.salvadoreduardo.ecommerce.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient viaCepRestClient(RestClient.Builder builder, @Value("${api.viacep.url}") String url) {
        return builder.baseUrl(url).build();
    }

    @Bean
    public RestClient brasilApiRestClient(RestClient.Builder builder, @Value("${api.brasilapi.url}") String url) {
        return builder.baseUrl(url).build();
    }
}