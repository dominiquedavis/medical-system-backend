package com.medicalsystem.model.value;

import com.medicalsystem.model.IdComparableEntity;
import com.medicalsystem.model.Patient;
import com.medicalsystem.model.field.Field;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * An abstract entity representing a specific values for the specific field for the specific patient.
 * @param <T> - the type of the values
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class FieldValue<U extends Field, T> extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    @Getter @Setter
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @Getter @Setter
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    @Getter @Setter
    private U field;

    @Transient
    @Getter @Setter
    private T value;


}
