package com.medicalsystem.model.value;

import com.medicalsystem.model.IdComparableEntity;
import com.medicalsystem.model.field.Field;
import lombok.*;

import javax.persistence.*;

@MappedSuperclass
public abstract class FieldValue<T extends Field<U>, U> extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter @Setter
    private int id;

    @OneToOne
    @JoinColumn(name = "field_id")
    @Getter @Setter
    private T field;

    @Column(name = "value")
    @Getter @Setter
    private U value;

}
