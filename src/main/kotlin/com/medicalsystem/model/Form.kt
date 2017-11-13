package com.medicalsystem.model

import javax.persistence.*

@Entity
@Table(name = "FORMS")
data class Form(

        @Id @GeneratedValue
        @Column(name = "ID")
        var id: Long = 0,

        @Column(name = "NAME")
        var name: String = "",

        @Column(name = "TYPE")
        var type: String = "",

        @Column(name = "SHEET_INDEX")
        var sheetIndex: Int = -1,

        @OneToMany(
                mappedBy = "form",
                fetch = FetchType.EAGER,
                cascade = arrayOf(CascadeType.ALL),
                orphanRemoval = true
        )
        var sections: MutableList<Section> = mutableListOf()
) {

    override fun toString(): String {
        return "Form(id=$id, name='$name')"
    }
}