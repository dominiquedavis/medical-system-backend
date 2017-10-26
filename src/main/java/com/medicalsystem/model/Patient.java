package com.medicalsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PATIENT")
@NoArgsConstructor
public class Patient extends IdComparableEntity {

    @Id
    @Column(name = "ID")
    @Getter @Setter
    private long id;

}
