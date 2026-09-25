package com.salvadoreduardo.ecommerce.service;
import com.salvadoreduardo.ecommerce.dto.request.AddressRequest;
import com.salvadoreduardo.ecommerce.dto.response.AddressResponse;
import com.salvadoreduardo.ecommerce.entity.Address;
import com.salvadoreduardo.ecommerce.exception.RuleException;
import com.salvadoreduardo.ecommerce.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final CepService cepService;

    public AddressResponse createAddress(AddressRequest request) {
        Address address = new Address();
        fillAddress(address, request);
        return AddressResponse.fromEntity(addressRepository.save(address));
    }

    public Page<AddressResponse> getAllAddress(Pageable pageable) {
        return addressRepository.findAll(pageable).map(AddressResponse::fromEntity);
    }

    public AddressResponse getAddressById(Long id) throws RuleException {
        return AddressResponse.fromEntity(findAddressById(id));
    }

    public AddressResponse updateAddress(Long id, AddressRequest request) throws RuleException {
        Address address = findAddressById(id);
        fillAddress(address, request);
        return AddressResponse.fromEntity(addressRepository.save(address));
    }

    public void deleteAddress(Long id) throws RuleException {
        addressRepository.delete(findAddressById(id));
    }

    private Address findAddressById(Long id) throws RuleException {
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuleException("Address not found"));
    }

    private void fillAddress(Address address, AddressRequest request) {
        AddressResponse found = cepService.findByCep(request.cep());
        address.setCep(found.cep());
        address.setState(found.state());
        address.setCity(found.city());
        address.setNeighborhood(found.neighborhood());
        address.setStreet(found.street());
        address.setNumber(request.number());
        address.setComplement(request.complement());
    }
}