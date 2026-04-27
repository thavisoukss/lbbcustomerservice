package com.lbb.customer.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> handleExpired(TokenExpiredException ex) {
        return Map.of(
                "code", "TOKEN_EXPIRED",
                "message", ex.getMessage(),
                "status", 401,
                "timestamp", LocalDateTime.now()
        );
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> handleInvalid(InvalidTokenException ex) {
        return Map.of(
                "code", "INVALID_TOKEN",
                "message", ex.getMessage(),
                "status", 401,
                "timestamp", LocalDateTime.now()
        );
    }

}
