package com.salvadoreduardo.ecommerce.controller;
import com.salvadoreduardo.ecommerce.dto.request.AddressRequest;
import com.salvadoreduardo.ecommerce.dto.response.AddressResponse;
import com.salvadoreduardo.ecommerce.exception.RuleException;
import com.salvadoreduardo.ecommerce.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressResponse> create(@Valid @RequestBody AddressRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.createAddress(request));
    }

    @GetMapping
    public ResponseEntity<Page<AddressResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(addressService.getAllAddress(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getById(@PathVariable Long id) throws RuleException {
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponse> update(@PathVariable Long id,
                                                  @Valid @RequestBody AddressRequest request) throws RuleException {
        return ResponseEntity.ok(addressService.updateAddress(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws RuleException {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}