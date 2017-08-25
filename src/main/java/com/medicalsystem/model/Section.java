package com.medicalsystem.model;

import com.medicalsystem.model.field.Field;
import lombok.*;

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

    /**
     * A list of forms that contain the section (could be more than one).
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "FORM_SECTION")
    @Getter @Setter
    private List<Form> forms = new ArrayList<>();

    /**
     * A list of fields that make up the section.
     */
    @ManyToMany(mappedBy = "sections", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @Getter @Setter
    private List<Field> fields = new ArrayList<>();

    public Section(String name) {
        this.name = name;
    }

    public void addForm(Form form) {
        forms.add(form);
    }

    public void addField(Field<?> field) {
        fields.add(field);
    }

}
