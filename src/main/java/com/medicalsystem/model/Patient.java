package com.medicalsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Patients")
@NoArgsConstructor
public class Patient {

    @Id
    @Getter @Setter
    private long id;

}
