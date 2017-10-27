package com.medicalsystem.factory.impl;

import com.medicalsystem.factory.FieldValueFactory;
import com.medicalsystem.model.FieldType;
import com.medicalsystem.model.fieldvalue.*;
import org.springframework.stereotype.Component;

@Component
public class FieldValueFactoryImpl implements FieldValueFactory {

    @Override
    public FieldValue<?> fromFieldType(FieldType fieldType) {
        switch (fieldType) {
            case DATE:
                return new DateFieldValue();
            case MULTIPLE_SELECT:
                return new MultipleSelectFieldValue();
            case NUMBER:
                return new NumberFieldValue();
            case SELECT:
                return new SelectFieldValue();
            case TEXT:
                return new TextFieldValue();
            default:
                throw new IllegalArgumentException("No such field type: " + fieldType);
        }
    }
}
