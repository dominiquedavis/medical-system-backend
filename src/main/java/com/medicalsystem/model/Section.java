package com.medicalsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SECTIONS")
@NoArgsConstructor
public class Section extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Getter @Setter
    private long id;

    @Column(name = "NAME")
    @Getter @Setter
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FORM_ID")
    @Getter @Setter
    private Form form;

    @OneToMany(
            mappedBy = "section",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Getter @Setter
    private List<Field> fields = new ArrayList<>();

}
