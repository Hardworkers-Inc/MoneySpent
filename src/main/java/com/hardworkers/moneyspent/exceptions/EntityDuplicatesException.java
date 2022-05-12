package com.hardworkers.moneyspent.exceptions;

public class EntityDuplicatesException extends RuntimeException {

    public EntityDuplicatesException(String entityName, String name) {
        super(String.format("%s with name %s already exist!", entityName, name));
    }
}
