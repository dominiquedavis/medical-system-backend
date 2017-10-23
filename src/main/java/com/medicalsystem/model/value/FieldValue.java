package com.medicalsystem.model.value;

import com.medicalsystem.json.model.JSONField;
import com.medicalsystem.model.FormType;
import com.medicalsystem.model.IdComparableEntity;
import com.medicalsystem.model.Patient;
import com.medicalsystem.model.field.Field;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Row;

import javax.persistence.*;
import java.util.List;

/**
 * An abstract entity representing a specific values for the specific field for the specific patient.
 * @param <T> - the type of the values
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class FieldValue<T> extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    @Getter @Setter
    private int id;

    /**
     * Patient to whom this value belongs to.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @Getter @Setter
    private Patient patient;

    /**
     * A reference to the field which this values is relevant to.
     */
    // TODO: Make LAZY work
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    @Getter @Setter
    private Field<?> field;

    /**
     * An actual values of the field.
     *
     * This field has the @Transient annotation, since its JPA mapping is handled by annotating getter in subclasses.
     */
    @Transient
    @Getter @Setter
    private T value;

    public JSONField createJSONField() {
        JSONField jsonField = new JSONField();

        jsonField.setId(field.getId());
        jsonField.setName(field.getName());
        jsonField.setType(field.getType());
        jsonField.setValues(getValues());
        jsonField.setPossibleValues(field.getPossibleValues());

        return jsonField;
    }

    public abstract List<?> getValues();

    public abstract void setStringValue(String value);

    public abstract void createValueCell(Row row, FormType formType);

}
