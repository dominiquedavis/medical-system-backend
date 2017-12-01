package com.medicalsystem.domain.value

import com.medicalsystem.domain.Patient
import com.medicalsystem.domain.id.LongIdComparableEntity
import com.medicalsystem.domain.template.Field
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Inheritance
import javax.persistence.ManyToOne

@Entity
@Inheritance
abstract class FieldValue<T>(
        @ManyToOne(fetch = FetchType.LAZY)
        var patient: Patient? = null,
        @ManyToOne(fetch = FetchType.LAZY)
        var field: Field? = null
) : LongIdComparableEntity() {
    abstract var value: T
}