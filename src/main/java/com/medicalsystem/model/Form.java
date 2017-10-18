package com.medicalsystem.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.medicalsystem.json.serializer.FormSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * An entity representing a single form (a single sheet in an excel file - OPEN, EVAR).
 */
@Entity
@Table(name = "FORMS")
@NoArgsConstructor
@JsonSerialize(using = FormSerializer.class)
public class Form extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @Getter @Setter
    private String name;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private FormType type;

    /**
     * A list of sections that make up the form.
     */
    @OneToMany(mappedBy = "form")
    @Getter @Setter
    private List<Section> sections = new ArrayList<>();

    public void addSection(Section section) {
        this.sections.add(section);
    }
}
