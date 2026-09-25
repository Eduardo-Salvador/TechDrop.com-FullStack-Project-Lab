package com.salvadoreduardo.ecommerce.client.viacep;
import com.salvadoreduardo.ecommerce.client.CepProvider;
import com.salvadoreduardo.ecommerce.dto.response.AddressResponse;
import com.salvadoreduardo.ecommerce.entity.Address;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import java.util.Optional;

@Component
public class ViaCepClient implements CepProvider {

    private final RestClient restClient;

    public ViaCepClient(@Qualifier("viaCepRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public Optional<Address> cepSearch(String cep) {
        ViaCepResponse response = restClient.get()
                .uri("/{cep}/json/", cep)
                .retrieve()
                .body(ViaCepResponse.class);

        if (response == null || Boolean.TRUE.equals(response.error())) {
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
    }
}