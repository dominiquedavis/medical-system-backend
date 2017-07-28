package com.medicalsystem.service;

import java.util.List;

public interface CRUDService<T, U> {

    List<T> findAll();

    T findById(U id);

    T saveOrUpdate(T t);

    void delete(T t);

    void deleteById(U id);

}
