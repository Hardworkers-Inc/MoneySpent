package com.hardworkers.moneyspent.service;

import java.util.List;

public interface BaseCrudService<T> {

    List<T> getAll();

    T get(Long id);

    T create(T t);

    T update(T t);

    void delete(T t);
}
