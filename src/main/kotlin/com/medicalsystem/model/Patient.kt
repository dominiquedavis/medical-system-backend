package com.medicalsystem.model

import javax.persistence.*

@Entity
@Table(name = "PATIENTS")
data class Patient(

        @Id
        @Column(name = "ID")
        var id: String,

        @OneToOne
        @JoinColumn(name = "FORM_ID")
        var form: Form? = null
)