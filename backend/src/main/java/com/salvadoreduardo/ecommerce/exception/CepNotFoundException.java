package com.salvadoreduardo.ecommerce.exception;

public class CepNotFoundException extends RuntimeException {

    public CepNotFoundException(String message) {
        super(message);
    }
}