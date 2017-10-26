package com.medicalsystem.service;

import java.util.List;

public interface CRUDService<T, U> {

    List<T> getAll();

    T getById(U id);

    boolean existsById(U id);

    T save(T t);

    void deleteById(U id);

}
