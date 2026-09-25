package com.salvadoreduardo.ecommerce.controller;
import com.salvadoreduardo.ecommerce.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cep")
public class AddressController {

    private final AddressService addressService;


}
