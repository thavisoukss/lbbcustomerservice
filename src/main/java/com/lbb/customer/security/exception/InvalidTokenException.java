package com.lbb.customer.security.exception;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException() {
        super("Invalid JWT token");
    }
}
