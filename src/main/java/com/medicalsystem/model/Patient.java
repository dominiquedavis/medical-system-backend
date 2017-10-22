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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @Getter @Setter
    private int patientId;

    public Patient(int patientId) {
        this.patientId = patientId;
    }

}
