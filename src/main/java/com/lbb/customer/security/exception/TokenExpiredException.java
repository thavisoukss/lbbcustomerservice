package com.lbb.customer.security.exception;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException() {
        super("JWT token has expired");
    }
}
