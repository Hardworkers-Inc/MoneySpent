package com.hardworkers.moneyspent.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entityName, Long id) {
        super(String.format("%s with id %d not found!", entityName, id));
    }

    public EntityNotFoundException(String entityName, String name) {
        super(String.format("%s with name %s not found!", entityName, name));
    }
}
