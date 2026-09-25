package com.salvadoreduardo.ecommerce.dto.response;

import com.salvadoreduardo.ecommerce.entity.Address;

public record AddressResponse(
        Long id,
        String cep,
        String state,
        String city,
        String neighborhood,
        String street,
        Integer number,
        String complement
) {
    public static AddressResponse fromEntity(Address address) {
        return new AddressResponse(
                address.getId(),
                address.getCep(),
                address.getState(),
                address.getCity(),
                address.getNeighborhood(),
                address.getStreet(),
                address.getNumber(),
                address.getComplement()
        );
    }

    public static AddressResponse fromLookup(String cep, String state, String city, String neighborhood, String street) {
        return new AddressResponse(null, cep, state, city, neighborhood, street, null, null);
    }
}