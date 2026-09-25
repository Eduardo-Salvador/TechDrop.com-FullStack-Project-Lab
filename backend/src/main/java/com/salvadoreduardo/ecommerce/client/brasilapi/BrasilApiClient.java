package com.salvadoreduardo.ecommerce.client.brasilapi;
import com.salvadoreduardo.ecommerce.client.CepProvider;
import com.salvadoreduardo.ecommerce.dto.response.AddressResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import java.util.Optional;

@Order(2)
@Component
public class BrasilApiClient implements CepProvider {

    private final RestClient restClient;

    public BrasilApiClient(@Qualifier("brasilApiRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public Optional<AddressResponse> cepSearch(String cep) {
        try {
            BrasilApiResponse response = restClient.get()
                    .uri("/{cep}", cep)
                    .retrieve()
                    .body(BrasilApiResponse.class);

            if (response == null) {
                return Optional.empty();
            }

            return Optional.of(AddressResponse.fromLookup(
                    cep,
                    response.state(),
                    response.city(),
                    response.neighborhood(),
                    response.street()
            ));
        } catch (HttpClientErrorException.NotFound e) {
            // A BrasilAPI responde 404 quando o CEP não existe
            return Optional.empty();
        }
    }
}