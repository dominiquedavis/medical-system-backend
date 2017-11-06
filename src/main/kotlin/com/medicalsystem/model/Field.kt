package com.medicalsystem.model

import javax.persistence.*

@Entity
@Table(name = "FIELDS")
data class Field(

        @Id @GeneratedValue
        @Column(name = "ID")
        var id: Long = 0,

        @Column(name = "NAME")
        var name: String = "",

        @Column(name = "TYPE")
        var type: FieldType = FieldType.TEXT,

        @ManyToOne
        @JoinColumn(name = "SECTION_ID")
        var section: Section? = null,

        @Column(name = "COLUMN_INDEX")
        var columnIndex: Int = -1,

        @ElementCollection(fetch = FetchType.EAGER)
        @CollectionTable(name = "POSSIBLE_VALUES", joinColumns = arrayOf(JoinColumn(name = "FIELD_ID")))
        @MapKeyColumn(name = "KEY")
        @Column(name = "VALUE")
        var possibleValues: Map<String, String> = mutableMapOf()
) {
    override fun toString(): String {
        return "Field(id=$id, name='$name')"
    }
}