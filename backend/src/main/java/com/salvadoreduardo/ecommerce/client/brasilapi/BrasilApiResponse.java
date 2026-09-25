package com.salvadoreduardo.ecommerce.client.brasilapi;

public record BrasilApiResponse(
        String cep,
        String state,
        String city,
        String neighborhood,
        String street
) {
}