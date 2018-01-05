package com.medicalsystem.domain.template

import com.medicalsystem.domain.id.LongIdComparableEntity
import javax.persistence.*

/**
 * A Form. This is the central class in the domain model, and it is the root of
 * the Form-Section-Field-FieldValue aggregate.
 *
 * The Form aggregate, and the entire domain model, is built to solve the
 * problem of mapping an excel spreadsheet to a database and managing the data.
 * The Form represent a single sheet in an excel spreadsheet.
 */
@Entity
@Table(uniqueConstraints = [ UniqueConstraint(columnNames = ["name", "sheetName"]) ])
class Form(
        var name: String = "",
        var sheetName: String = "",

        @OneToMany(mappedBy = "form", cascade = [ CascadeType.ALL ], orphanRemoval = true)
        var sections: MutableSet<Section> = mutableSetOf()

) : LongIdComparableEntity() {

    fun addSection(section: Section) {
        sections.add(section)
        section.form = this
    }
}