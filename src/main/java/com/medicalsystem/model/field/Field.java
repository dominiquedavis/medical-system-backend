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
 * A superclass for every type of field.
 * Subclasses must override "options" getter providing JPA annotations for the join table.
 * @param <T> - the type of the value stored in the field
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
public abstract class Field<T> extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    @Getter @Setter
    private int id;

    @Getter @Setter
    private String name;

    /**
     * An index of the column in the excel file corresponding to the field.
     */
    @Getter @Setter
    private int excelColumn;

    /**
     * A list of sections that contain the field (could be more than one).
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "FIELD_SECTION")
    @Getter @Setter
    private List<Section> sections = new ArrayList<>();

    /**
     * A map describing values that the field can acquire.
     * Key   - value of the cell in the excel file (e.g. "0", "1", "2", "x")
     * Value - a 'literal' value for the corresponding key (e.g. "smoker", "non-smoker", "n/a")
     *
     * The map remains empty if the field has no restrictions on the possible values.
     *
     * This field has the @Transient annotation, since its JPA mapping is handled by annotating getter in subclasses.
     */
    @Transient
    @Getter @Setter
    private Map<String, T> options = new HashMap<>();

    public Field(String name, int excelColumn, Map<String, T> options) {
        this.name = name;
        this.excelColumn = excelColumn;
        this.options = (options == null) ? new HashMap<>() : options;
    }

    public void addOption(String key, T value) {
        options.put(key, value);
    }

    public void addSection(Section section) {
        sections.add(section);
    }

}
