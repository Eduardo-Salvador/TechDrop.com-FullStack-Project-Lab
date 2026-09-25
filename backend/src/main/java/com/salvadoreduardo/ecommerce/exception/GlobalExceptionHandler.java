package com.salvadoreduardo.ecommerce.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> handleException(MethodArgumentNotValidException e) {
        List<String> messages = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ResponseError responseError = new ResponseError(
                java.time.LocalDateTime.now(),
                HttpStatus.BAD_GATEWAY.value(),
                "Validation Error",
                messages
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    @ExceptionHandler(RuleException.class)
    public ResponseEntity<ResponseError> handleException(RuleException e) {
        ResponseError responseError = new ResponseError(
                java.time.LocalDateTime.now(),
                HttpStatus.BAD_GATEWAY.value(),
                "Business Rule Error",
                List.of(e.getMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    @ExceptionHandler(InvalidCepException.class)
    public ResponseEntity<ResponseError> handleInvalidCep(InvalidCepException e) {
        ResponseError responseError = new ResponseError(
                java.time.LocalDateTime.now(),
                HttpStatus.BAD_GATEWAY.value(),
                "Input rule Error",
                List.of(e.getMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    @ExceptionHandler(CepNotFoundException.class)
    public ResponseEntity<ResponseError> handleCepNotFound(CepNotFoundException e) {
        ResponseError responseError = new ResponseError(
                java.time.LocalDateTime.now(),
                HttpStatus.BAD_GATEWAY.value(),
                "Input rule Error",
                List.of(e.getMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }
}