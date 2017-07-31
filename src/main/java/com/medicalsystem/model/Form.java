package com.medicalsystem.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "FORMS")
public class Form extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "form_id")
    @Getter @Setter
    private int id;

    @ManyToMany(mappedBy = "forms", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @Getter @Setter
    private List<Section> sections = new ArrayList<>();

}
