package com.medicalsystem.model.field;

import com.medicalsystem.model.IdComparableEntity;
import com.medicalsystem.model.Section;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Super class for every type of the field.
 * Subclasses must override "options" getter providing JPA annotations for the join table.
 * @param <T> - the type of the value stored in the field
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Field<T> extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    @Getter @Setter
    private int id;

    @Column(name = "name")
    @Getter @Setter
    private String name;

    @Column(name = "excel_column")
    @Getter @Setter
    private int excelColumn;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "FIELD_SECTION")
    @Getter @Setter
    private List<Section> sections = new ArrayList<>();

    @Transient
    @Getter @Setter
    private Map<String, T> options = new HashMap<>();

    public void addOption(String key, T value) {
        options.put(key, value);
    }

    public void addSection(Section section) {
        sections.add(section);
    }

}
