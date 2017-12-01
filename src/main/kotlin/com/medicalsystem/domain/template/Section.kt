package com.medicalsystem.domain.template

import com.medicalsystem.domain.id.LongIdComparableEntity
import javax.persistence.*

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = arrayOf("name"))])
class Section(
    var name: String = "",
    @OneToMany(
        mappedBy = "section",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var fields: MutableSet<Field> = mutableSetOf(),
    @ManyToOne(fetch = FetchType.LAZY)
    var form: Form? = null
) : LongIdComparableEntity() {

    fun addField(field: Field) {
        fields.add(field)
        field.section = this
    }
}