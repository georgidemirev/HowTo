package com.demirev.rest.exceptions;

public class ValidationException extends Exception {
    public ValidationException(String errorMessage) {
        super(errorMessage);
    }
}
