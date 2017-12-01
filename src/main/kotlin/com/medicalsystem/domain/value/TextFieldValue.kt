package com.medicalsystem.domain.value

import javax.persistence.Column
import javax.persistence.Entity

@Entity
class TextFieldValue(
    @Column(name = "TEXT_VALUE")
    override var value: String = "") : FieldValue<String>()