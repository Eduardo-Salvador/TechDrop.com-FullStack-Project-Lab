package com.salvadoreduardo.ecommerce.dto.response;
import com.salvadoreduardo.ecommerce.entity.Address;

public record AddressResponse(
        String cep,
        String state,
        String city,
        String neighborhood,
        String street,
        String complement
) {
    public static AddressResponse fromEntity(Address address) {
        return new AddressResponse(
                address.getCep(),
                address.getState(),
                address.getCity(),
                address.getNeighborhood(),
                address.getStreet(),
                address.getComplement()
        );
    }
}