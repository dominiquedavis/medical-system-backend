package com.medicalsystem.domain

import com.medicalsystem.domain.id.StringIdComparableEntity
import com.medicalsystem.domain.template.Form
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
class Patient(
        id: String,
        @ManyToOne(fetch = FetchType.LAZY)
        var form: Form? = null
) : StringIdComparableEntity(id)