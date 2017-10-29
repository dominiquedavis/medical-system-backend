package com.medicalsystem.mapper;

import com.medicalsystem.model.Patient;

import java.util.List;

public interface BasicMapper<T, U> {
    List<U> toJSONs(List<T> ts);
    U toJSON(T t);
    List<U> toJSONs(List<T> ts, Patient patient);
    U toJSON(T t, Patient patient);
    T fromJSON(U u);

}
