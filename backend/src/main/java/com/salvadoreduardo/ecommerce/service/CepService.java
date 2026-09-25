package com.salvadoreduardo.ecommerce.service;
import com.salvadoreduardo.ecommerce.client.CepProvider;
import com.salvadoreduardo.ecommerce.dto.response.AddressResponse;
import com.salvadoreduardo.ecommerce.exception.CepNotFoundException;
import com.salvadoreduardo.ecommerce.exception.InvalidCepException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CepService {

    private static final String CEP_PATTERN = "^\\d{5}-?\\d{3}$";
    
    private final List<CepProvider> providers;

    public AddressResponse findByCep(String cep) {
        if (cep == null || !cep.matches(CEP_PATTERN)) {
            throw new InvalidCepException("CEP inválido: " + cep);
        }

        String cleanCep = cep.replace("-", "");

        for (CepProvider provider : providers) {
            try {
                Optional<AddressResponse> address = provider.cepSearch(cleanCep);
                if (address.isPresent()) {
                    return address.get();
                }
            } catch (RestClientException e) {
                // API fora do ar, timeout ou erro 5xx: tenta o próximo provedor
                log.warn("Falha ao consultar {}: {}", provider.getClass().getSimpleName(), e.getMessage());
            }
        }

        throw new CepNotFoundException("CEP não encontrado: " + cleanCep);
    }
}