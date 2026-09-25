package com.salvadoreduardo.ecommerce.client.viacep;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ViaCepResponse(
        String cep,
        @JsonProperty("logradouro")
        String street,
        @JsonProperty("bairro")
        String neighborhood,
        @JsonProperty("localidade")
        String city,
        @JsonProperty("uf")
        String state,
        @JsonProperty("erro")
        Boolean error
) {
}