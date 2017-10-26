package com.medicalsystem.model.fieldvalue;

import com.medicalsystem.model.IdComparableEntity;
import com.medicalsystem.model.Patient;
import com.medicalsystem.model.Field;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class FieldValue<T> extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "ID")
    @Getter @Setter
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PATIENT_ID")
    @Getter @Setter
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FIELD_ID")
    @Getter @Setter
    private Field field;

    @Transient
    @Getter @Setter
    private T value;

}
