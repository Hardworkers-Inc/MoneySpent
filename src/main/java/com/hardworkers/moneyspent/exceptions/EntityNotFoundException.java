package com.hardworkers.moneyspent.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entityName, Long id) {
        super(String.format("%s with id %d not found!", entityName, id));
    }
}
