package com.medicalsystem.model.field;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "DATE_FIELDS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
public class DateField extends Field {

    @Override
    public FieldType getType() {
        return FieldType.DATE;
    }

}
