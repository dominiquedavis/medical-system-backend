package com.medicalsystem.model.value

import javax.persistence.*

@Entity
@Table(name = "TEXT_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
class TextFieldValue : FieldValue<String>() {

    @Column(name = "VALUE")
    override var value: String = ""

    override fun setValueFromString(s: String) {
        this.value = s
    }
}