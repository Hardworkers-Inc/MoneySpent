package com.hardworkers.moneyspent.exceptions;

public class EntityValidationFailedException extends RuntimeException {

    public EntityValidationFailedException(String message) {
        super(message);
    }
}
