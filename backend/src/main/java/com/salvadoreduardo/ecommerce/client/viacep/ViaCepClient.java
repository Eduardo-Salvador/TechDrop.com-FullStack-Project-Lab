package com.salvadoreduardo.ecommerce.client.viacep;
import com.salvadoreduardo.ecommerce.client.CepProvider;
import com.salvadoreduardo.ecommerce.dto.response.AddressResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import java.util.Optional;

@Order(1)
@Component
public class ViaCepClient implements CepProvider {

    private final RestClient restClient;

    public ViaCepClient(@Qualifier("viaCepRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public Optional<AddressResponse> cepSearch(String cep) {
        ViaCepResponse response = restClient.get()
                .uri("/{cep}/json/", cep)
                .retrieve()
                .body(ViaCepResponse.class);

        // A ViaCEP responde 200 com {"erro": true} quando o CEP não existe
        if (response == null || Boolean.TRUE.equals(response.error())) {
            return Optional.empty();
        }

        return Optional.of(AddressResponse.fromLookup(
                cep,
                response.state(),
                response.city(),
                response.neighborhood(),
                response.street()
        ));
    }
}