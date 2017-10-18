package com.medicalsystem.model.field;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.medicalsystem.model.FormType;
import com.medicalsystem.model.IdComparableEntity;
import com.medicalsystem.serializer.FieldSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * A superclass for every type of field.
 * Subclasses must override "options" getter providing JPA annotations for the join table.
 * @param <T> - the type of the values stored in the field
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@JsonSerialize(using = FieldSerializer.class)
public abstract class Field<T> extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    @Getter @Setter
    private int id;

    @Getter @Setter
    private String name;

    /**
     * An index of the column in the excel file corresponding to the field in OPEN sheet.
     */
    @Getter @Setter
    private int openExcelColumn;

    /**
     * An index of the column in the excel file corresponding to the field in EVAR sheet.
     */
    @Getter @Setter
    private int evarExcelColumn;

    /**
     * Indicates if the field can contain multiple values
     */
    @Getter @Setter
    private boolean multiple = false;

    /**
     * A map describing values that the field can acquire.
     * Key   - values of the cell in the excel file (e.g. "0", "1", "2", "x")
     * Value - a 'literal' values for the corresponding key (e.g. "smoker", "non-smoker", "n/a")
     *
     * The map remains empty if the field has no restrictions on the possible values.
     *
     * This field has the @Transient annotation, since its JPA mapping is handled by annotating getter in subclasses.
     */
    @Transient
    @Getter @Setter
    private Map<T, String> options = new HashMap<>();


    /**
     * Constructor
     *
     * @param name            name of the field
     * @param openExcelColumn index of the corresponding excel column in OPEN sheet
     * @param evarExcelColumn index of the corresponding excel column in EVAR sheet
     * @param options         a map of possible options (null if none)
     */
    public Field(String name, int openExcelColumn, int evarExcelColumn, Map<T, String> options) {
        this.name = name;
        this.openExcelColumn = openExcelColumn;
        this.evarExcelColumn = evarExcelColumn;
        this.options = (options == null) ? new HashMap<>() : options;
    }

    public void addOption(T key, String value) {
        this.options.put(key, value);
    }

    /**
     * Abstract method for adding option with a String representation of the key.
     * The key should be converted to a proper type in subclasses.
     *
     * @param key   a String representation of the key
     * @param value values
     */
    public abstract void addOption(String key, String value);

    public boolean isOpen() {
        return this.openExcelColumn != -1;
    }

    public boolean isEvar() {
        return this.evarExcelColumn != -1;
    }

    public String getType() {

        if (multiple)
            return "MULTIPLE_SELECT";

        if (!options.isEmpty())
            return "SELECT";

        String className = this.getClass().getSimpleName().replace("Field", "");

        if (className.equals("Integer") || className.equals("Double"))
            return "NUMBER";

        return className.toUpperCase();
    }

}
