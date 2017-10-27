package com.medicalsystem.factory;

import java.util.List;

public interface BasicFactory<T, U> {

    List<T> fromProperties(List<U> us);

    T fromProperties(U u);

}
