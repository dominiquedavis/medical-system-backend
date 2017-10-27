package com.medicalsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(
        name = "PATIENT",
        indexes = { @Index(name = "ID_INDEX", columnList = "ID", unique = true) }
)
@NoArgsConstructor
public class Patient extends IdComparableEntity {

    @Id
    @Column(name = "ID")
    @Getter @Setter
    private long id;

    public Patient(long id) {
        this.id = id;
    }
}
