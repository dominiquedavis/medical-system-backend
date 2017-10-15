package com.medicalsystem.model.value;

import com.medicalsystem.model.IdComparableEntity;
import com.medicalsystem.model.field.Field;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * An abstract entity representing a specific values for the specific field for the specific patient.
 * @param <T> - the type of the values
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class FieldValue<T> extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    @Getter @Setter
    private int id;

    /**
     * An ID of the patient this values belongs to.
     */
    @Getter @Setter
    private int patientId;

    /**
     * A reference to the field which this values is relevant to.
     */
    // TODO: Make LAZY work
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    @Getter @Setter
    private Field field;

    /**
     * An actual values of the field.
     *
     * This field has the @Transient annotation, since its JPA mapping is handled by annotating getter in subclasses.
     */
    @Transient
    @Getter @Setter
    private T values;

}
