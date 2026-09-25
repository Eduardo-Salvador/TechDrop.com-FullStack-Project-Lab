package com.salvadoreduardo.ecommerce.client.brasilapi;
import com.salvadoreduardo.ecommerce.client.CepProvider;
import com.salvadoreduardo.ecommerce.dto.response.AddressResponse;
import com.salvadoreduardo.ecommerce.entity.Address;
import com.salvadoreduardo.ecommerce.exception.InvalidCepException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Component
public class BrasilApiClient implements CepProvider {
    private final RestClient restClient;

    public BrasilApiClient(@Qualifier("brasilApiRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public Optional<Address> cepSearch(String cep) {
        try {
            BrasilApiResponse response = restClient.get()
                    .uri("/{cep}", cep)
                    .retrieve()
                    .body(BrasilApiResponse.class);

            if (response == null || response.error() != null) {
                return Optional.empty();
            }
            return Optional.of(new Address(
                    response.cep(),
                    response.state(),
                    response.street(),
                    response.neighborhood(),
                    response.city(),
                    response.state()
            ));
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        }
    }
}