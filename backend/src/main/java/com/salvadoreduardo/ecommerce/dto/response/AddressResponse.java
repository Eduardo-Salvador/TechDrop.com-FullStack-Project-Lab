package com.salvadoreduardo.ecommerce.dto.response;

import java.util.Optional;

public record AddressResponse(
        String state,
        String city,
        String neighborhood,
        String street
) {

}
