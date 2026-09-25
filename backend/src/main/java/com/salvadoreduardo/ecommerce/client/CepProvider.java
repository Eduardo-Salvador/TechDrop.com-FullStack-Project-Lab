package com.salvadoreduardo.ecommerce.client;
import com.salvadoreduardo.ecommerce.dto.response.AddressResponse;
import java.util.Optional;

public interface CepProvider {

    Optional<AddressResponse> cepSearch(String cep);
}