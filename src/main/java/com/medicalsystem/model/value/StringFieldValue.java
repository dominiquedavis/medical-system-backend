package com.medicalsystem.model.value;

import com.medicalsystem.model.field.StringField;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "STRING_FIELD_VALUES")
public class StringFieldValue extends FieldValue<StringField, String> {
}
