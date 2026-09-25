package com.salvadoreduardo.ecommerce.service;
import com.salvadoreduardo.ecommerce.client.brasilapi.BrasilApiClient;
import com.salvadoreduardo.ecommerce.client.viacep.ViaCepClient;
import com.salvadoreduardo.ecommerce.dto.request.AddressRequest;
import com.salvadoreduardo.ecommerce.dto.request.CategoryRequest;
import com.salvadoreduardo.ecommerce.dto.response.AddressResponse;
import com.salvadoreduardo.ecommerce.dto.response.CategoryResponse;
import com.salvadoreduardo.ecommerce.entity.Address;
import com.salvadoreduardo.ecommerce.entity.Category;
import com.salvadoreduardo.ecommerce.exception.InvalidCepException;
import com.salvadoreduardo.ecommerce.exception.RuleException;
import com.salvadoreduardo.ecommerce.repository.AddressRepository;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final ViaCepClient viaCepClient;
    private final BrasilApiClient brasilApiClient;

    public AddressResponse createAddress(String cep) throws InvalidCepException {
        if (!cep.matches("^\\d{5}-?\\d{3}$")) throw new InvalidCepException("CEP inválido: " + cep);
        Optional<Address> getResponse = viaCepClient.cepSearch(cep);

        if (getResponse.isPresent()) {
            Address address = getResponse.get();
            return AddressResponse.fromEntity(addressRepository.save(address));
        }
        getResponse = brasilApiClient.cepSearch(cep);
        if (getResponse.isPresent()) {
            Address address = getResponse.get();
            return AddressResponse.fromEntity(addressRepository.save(address));
        }
        return null;
    }

    public Page<AddressResponse> getAllAddress(Pageable pageable) throws RuleException {
        return addressRepository.findAll(pageable).map(AddressResponse::fromEntity);
    }

    private AddressResponse getAddressById(Long id) throws RuleException {
        return AddressResponse.fromEntity(findAddressById(id));
    }

    public AddressResponse updateAddress(Long id, AddressRequest request) throws RuleException {
        Address address = findAddressById(id);
        if (!request.cep().matches("^\\d{5}-?\\d{3}$")) throw new InvalidCepException("CEP inválido: " + cep);
        Optional<Address> getResponse = viaCepClient.cepSearch(request.cep());
        if (getResponse.isPresent()) {
            Address newAddress = getResponse.get();
            return AddressResponse.fromEntity(addressRepository.save(newAddress));
        }
        getResponse = brasilApiClient.cepSearch(request.cep());
        if (getResponse.isPresent()) {
            Address newAddress = getResponse.get();
            return AddressResponse.fromEntity(addressRepository.save(newAddress));
        }
        return null;
    }

    public void deleteAddress(Long id) throws RuleException {
        addressRepository.delete(findAddressById(id));
    }

    private Address findAddressById(Long id) throws RuleException {
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuleException("Address not found"));
    }






}
