package com.medicalsystem.service;

import java.util.List;

/**
 * An interface providing methods for basic CRUD operations
 * @param <T> - the type of the entity
 * @param <U> - the type of ID of the entity
 */
public interface CRUDService<T, U> {

    List<T> findAll();

    T findById(U id);

    T saveOrUpdate(T t);

    void delete(T t);

    void deleteById(U id);

}
