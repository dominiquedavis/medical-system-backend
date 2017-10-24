package com.medicalsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "FORMS")
@NoArgsConstructor
public class Form extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @Getter @Setter
    private String name;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private FormType type;

    @OneToMany(mappedBy = "form")
    @Getter @Setter
    private List<Section> sections = new ArrayList<>();

}
