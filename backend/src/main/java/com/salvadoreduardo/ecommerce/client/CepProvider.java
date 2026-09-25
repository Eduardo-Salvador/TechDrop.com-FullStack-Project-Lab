package com.salvadoreduardo.ecommerce.client;

import com.salvadoreduardo.ecommerce.dto.response.AddressResponse;
import com.salvadoreduardo.ecommerce.entity.Address;

import java.util.Optional;

public interface CepProvider {
    Optional<Address> cepSearch(String cep);
}