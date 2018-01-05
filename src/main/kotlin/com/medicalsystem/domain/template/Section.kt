package com.medicalsystem.domain.template

import com.medicalsystem.domain.id.LongIdComparableEntity
import javax.persistence.*

/**
 * An entity being an aggregate for a group of Field entities (e.g. 'Personal data', 'Admission').
 */
@Entity
class Section(
        var name: String = "",

        @ManyToOne(fetch = FetchType.LAZY)
        var form: Form? = null,

        @OneToMany(mappedBy = "section", cascade = [ CascadeType.ALL ], orphanRemoval = true)
        var fields: MutableSet<Field> = mutableSetOf()

) : LongIdComparableEntity() {

    fun addField(field: Field) {
        fields.add(field)
        field.section = this
    }
}