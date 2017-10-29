package com.medicalsystem.model.fieldvalue;

import com.medicalsystem.model.Field;
import com.medicalsystem.model.IdComparableEntity;
import com.medicalsystem.model.Patient;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class FieldValue<T> extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "ID")
    @Getter @Setter
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PATIENT_ID")
    @Getter @Setter
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FIELD_ID")
    @Getter @Setter
    private Field field;

    @Transient
    @Getter @Setter
    private T value;


    public abstract void setValueFromString(String str);

    public void createCell(Row row){
        Cell cell = row.createCell(getField().getExcelColumnIndex());
        createCellValue(cell);
    }

    protected abstract void createCellValue(Cell cell);
}
