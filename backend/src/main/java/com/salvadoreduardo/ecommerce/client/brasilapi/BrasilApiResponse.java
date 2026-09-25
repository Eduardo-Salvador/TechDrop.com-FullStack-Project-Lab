package com.salvadoreduardo.ecommerce.client.brasilapi;
import com.fasterxml.jackson.annotation.JsonProperty;

public record BrasilApiResponse(
        String cep,
        String state,
        String city,
        String neighborhood,
        String street,
        @JsonProperty("name")
        String error
) {
}
