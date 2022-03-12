package com.hardworkers.moneyspent;

public interface BaseCrudService<T> {

    Iterable<T> getAll();

    T getById(Long id);

    T create(T t);

    T update(T t);

    void delete(Long id);
}
