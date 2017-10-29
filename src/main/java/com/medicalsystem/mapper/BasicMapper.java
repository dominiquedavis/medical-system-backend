package com.medicalsystem.mapper;

import java.util.List;

public interface BasicMapper<T, U> {

    List<U> toJSONs(List<T> ts);

    U toJSON(T t);

}
