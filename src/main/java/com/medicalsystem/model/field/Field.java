package com.medicalsystem.model.field;

import com.medicalsystem.model.IdComparableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
public abstract class Field extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    @Getter @Setter
    private long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private int openExcelColumn;

    @Getter @Setter
    private int evarExcelColumn;

    public abstract FieldType getType();

}
