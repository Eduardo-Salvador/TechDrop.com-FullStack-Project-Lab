package com.salvadoreduardo.ecommerce.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressRequest(
        @NotBlank
        String cep,
        @NotNull
        Integer number,
        String complement
) {
}