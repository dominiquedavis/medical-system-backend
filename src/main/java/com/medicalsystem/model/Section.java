package com.medicalsystem.model;

import com.medicalsystem.model.field.Field;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * An entity representing a single section in the form.
 * Examples: personal data, admission, operation...
 */
@Entity
@Table(name = "SECTIONS")
@NoArgsConstructor
public class Section extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @Getter @Setter
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @Getter @Setter
    private Form form;

    /**
     * A list of fields that make up the section.
     */
    // TODO: Fix LAZY loading
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "SECTION_FIELD")
    @Getter @Setter
    private List<Field<?>> fields = new ArrayList<>();

    public Section(String name) {
        this.name = name;
    }

    public void addField(Field<?> field) {
        fields.add(field);
    }

}
