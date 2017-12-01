package com.medicalsystem.domain.value

import javax.persistence.Column
import javax.persistence.Entity

@Entity
class NumberFieldValue(
    @Column(name = "NUMBER_VALUE")
    override var value: Double = -1.0) : FieldValue<Double>()