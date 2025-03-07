package com.example.user_service.domain.exception;

public class ValidationException  extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
