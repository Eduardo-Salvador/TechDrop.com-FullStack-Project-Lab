package com.salvadoreduardo.ecommerce.controller;
import com.salvadoreduardo.ecommerce.dto.response.AddressResponse;
import com.salvadoreduardo.ecommerce.service.CepService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cep")
@RequiredArgsConstructor
public class CepController {

    private final CepService cepService;

    @GetMapping("/{cep}")
    public ResponseEntity<AddressResponse> findByCep(@PathVariable String cep) {
        return ResponseEntity.ok(cepService.findByCep(cep));
    }
}