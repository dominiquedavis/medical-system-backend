package com.medicalsystem.model

import javax.persistence.*

@Entity
@Table(name = "SECTIONS")
data class Section(

        @Id @GeneratedValue
        @Column(name = "ID")
        var id: Long = 0,

        @Column(name = "NAME")
        var name: String = "",

        @ManyToOne
        @JoinColumn(name = "FORM_ID")
        var form: Form? = null,

        @OneToMany(
                mappedBy = "section",
                fetch = FetchType.EAGER,
                cascade = arrayOf(CascadeType.ALL),
                orphanRemoval = true
        )
        var fields: MutableList<Field> = mutableListOf()
) {
    override fun toString(): String {
        return "Section(id=$id, name='$name')"
    }
}