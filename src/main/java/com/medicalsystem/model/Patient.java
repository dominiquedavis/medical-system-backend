package com.medicalsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * An entity class to store patient ID info
 */
@Entity
@Table(name = "PATIENTS")
@NoArgsConstructor
public class Patient {

    /**
     * Patient's hospital ID
     */
    @Id
    @Getter @Setter
    private int id;

    /**
     * Type of the form this patient applies to
     */
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private FormType formType;

    public Patient(int id, FormType formType) {
        this.id = id;
        this.formType = formType;
    }

}
