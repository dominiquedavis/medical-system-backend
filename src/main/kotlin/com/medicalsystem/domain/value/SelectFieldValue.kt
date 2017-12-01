package com.medicalsystem.domain.value

import com.medicalsystem.domain.template.ValueOption
import javax.persistence.*

@Entity
class SelectFieldValue(
    @ManyToOne(fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "SELECT_VALUE")
    override var value: ValueOption? = null) : FieldValue<ValueOption?>()