package com.medicalsystem.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * An entity representing a single form (a single sheet in an excel file - OPEN, EVAR and possibly other).
 */
@Entity
@Table(name = "FORMS")
public class Form extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @Getter @Setter
    private String name;

    /**
     * A list of sections that make up the form.
     */
    @ManyToMany(mappedBy = "forms", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @Getter @Setter
    private List<Section> sections = new ArrayList<>();

}
