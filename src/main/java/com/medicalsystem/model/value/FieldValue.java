package com.medicalsystem.model.value;

import com.medicalsystem.model.IdComparableEntity;
import com.medicalsystem.model.field.Field;
import lombok.*;

import javax.persistence.*;

/**
 * An abstract entity representing a specific value for the specific field for the specific patient.
 * @param <T> - the type of the field
 * @param <U> - the type of the values stored by the field
 */
@MappedSuperclass
public abstract class FieldValue<T extends Field<U>, U> extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    /**
     * An ID of the patient this value belongs to.
     */
    @Getter @Setter
    private int patientId;

    /**
     * A reference to the field which this value is relevant to.
     */
    @OneToOne
    @JoinColumn
    @Getter @Setter
    private T field;

    /**
     * An actual value of the field.
     */
    @Column
    @Getter @Setter
    private U value;

}
