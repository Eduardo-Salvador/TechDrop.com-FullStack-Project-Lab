package com.salvadoreduardo.ecommerce.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import java.time.Duration;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient viaCepRestClient(RestClient.Builder builder, @Value("${api.viacep.url}") String url) {
        return builder
                .baseUrl(url)
                .requestFactory(requestFactoryWithTimeout())
                .build();
    }

    @Bean
    public RestClient brasilApiRestClient(RestClient.Builder builder, @Value("${api.brasilapi.url}") String url) {
        return builder
                .baseUrl(url)
                .requestFactory(requestFactoryWithTimeout())
                .build();
    }

    private SimpleClientHttpRequestFactory requestFactoryWithTimeout() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofSeconds(3));
        factory.setReadTimeout(Duration.ofSeconds(3));
        return factory;
    }
}