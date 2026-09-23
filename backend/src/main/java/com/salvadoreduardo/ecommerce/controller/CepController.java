package com.salvadoreduardo.ecommerce.controller;
import com.salvadoreduardo.ecommerce.service.CepService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cep")
public class CepController {

    private final CepService cepService;


}
