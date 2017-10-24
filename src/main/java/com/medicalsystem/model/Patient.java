package com.medicalsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PATIENTS")
@NoArgsConstructor
public class Patient {

    @Id
    @Getter @Setter
    private long id;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private FormType formType;

}
