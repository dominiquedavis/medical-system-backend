package com.medicalsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(
        name = "PATIENTS",
        indexes = { @Index(name = "ID_INDEX", columnList = "ID", unique = true) }
)
@NoArgsConstructor
public class Patient extends IdComparableEntity {

    @Id
    @Column(name = "ID")
    @Getter @Setter
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FORM_ID")
    @Getter @Setter
    private Form form;
}
