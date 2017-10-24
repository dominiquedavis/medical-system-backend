package com.medicalsystem.model.value;

import com.medicalsystem.model.IdComparableEntity;
import com.medicalsystem.model.Patient;
import com.medicalsystem.model.field.Field;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class FieldValue<U extends Field, T> extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    @Getter @Setter
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @Getter @Setter
    private Patient patient;

    @Transient
    @Getter @Setter
    private U field;

    @Transient
    @Getter @Setter
    private T value;

}
