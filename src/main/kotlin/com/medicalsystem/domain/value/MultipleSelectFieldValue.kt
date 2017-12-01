package com.medicalsystem.domain.value

import com.medicalsystem.domain.template.ValueOption
import javax.persistence.*

@Entity
class MultipleSelectFieldValue(
    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(name = "field_value_value_option",
        joinColumns = [JoinColumn(name = "field_value_id")],
        inverseJoinColumns = [JoinColumn(name = "value_option_id")]
    )
    override var value: MutableSet<ValueOption> = mutableSetOf()) : FieldValue<MutableSet<ValueOption>>()
