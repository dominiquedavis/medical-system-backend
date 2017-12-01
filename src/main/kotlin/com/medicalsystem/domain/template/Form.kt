package com.medicalsystem.domain.template

import com.medicalsystem.domain.id.LongIdComparableEntity
import javax.persistence.*

/**
 * A Form. This is the central class in the domain model, and it is the root of
 * the Form-Section-Field-FieldValue aggregate.
 *
 * A template is identified by a unique template id, and it always has a name,
 * a type and a sheet index. It also contains multiple sections.
 *
 * The template aggregate, and the entire domain model, is built to solve the
 * problem of mapping an excel spreadsheet to a database and managing the data.
 * The template represent a single sheet in an excel spreadsheet.
 */
@Entity
@Table(uniqueConstraints = [
    UniqueConstraint(columnNames = ["name", "sheetIndex"])
])
class Form(
    var name: String = "",
    var sheetIndex: Int = -1,
    @OneToMany(
        mappedBy = "form",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var sections: MutableSet<Section> = mutableSetOf()
) : LongIdComparableEntity() {

    fun addSection(section: Section) {
        sections.add(section)
        section.form = this
    }
}