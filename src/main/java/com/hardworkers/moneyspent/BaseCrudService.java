package com.hardworkers.moneyspent;

import java.util.List;

public interface BaseCrudService<T> {

    Iterable<T> getAll();

    T get(Long id);

    T create(T t);

    T update(T t);

    void delete(Long id);
}
