package com.medicalsystem.factory;

import com.medicalsystem.model.FieldType;
import com.medicalsystem.model.fieldvalue.FieldValue;

public interface FieldValueFactory {

    FieldValue<?> fromFieldType(FieldType fieldType);

}
