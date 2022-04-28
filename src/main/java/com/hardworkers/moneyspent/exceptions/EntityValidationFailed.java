package com.hardworkers.moneyspent.exceptions;

public class EntityValidationFailed extends RuntimeException {

    public EntityValidationFailed(String message) {
        super(message);
    }
}
