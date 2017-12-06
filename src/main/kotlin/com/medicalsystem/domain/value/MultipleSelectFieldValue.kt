package com.medicalsystem.domain.value

import com.medicalsystem.domain.template.ValueOption
import com.medicalsystem.util.getByKey
import javax.persistence.*

@Entity
class MultipleSelectFieldValue(
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(name = "field_value_value_option",
        joinColumns = [JoinColumn(name = "field_value_id")],
        inverseJoinColumns = [JoinColumn(name = "value_option_id")]
    )
    override var value: MutableSet<ValueOption> = mutableSetOf()) : FieldValue<MutableSet<ValueOption>>() {

    override fun setValueFromString(stringValue: String) {
        if (stringValue != "") {
            if (stringValue.endsWith("11")) {
                value.add(getPossibleValues().getByKey("11"))
            }
            (0 until stringValue.length - "11".length)
                    .map { stringValue[it].toString() }
                    .forEach { value.add(getPossibleValues().getByKey(it)) }
        }
    }
}
