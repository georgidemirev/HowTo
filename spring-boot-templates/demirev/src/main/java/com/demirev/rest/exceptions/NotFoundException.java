package com.demirev.rest.exceptions;

public class NotFoundException extends Exception {
    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
