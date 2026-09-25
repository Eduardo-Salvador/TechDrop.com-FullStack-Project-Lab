package com.salvadoreduardo.ecommerce.dto.request;

import jakarta.validation.constraints.NotNull;

public record AddressRequest(
        @NotNull
        String cep
) {
    public Ad
}
