package com.medicalsystem.model.value;

import com.medicalsystem.model.field.Field;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
public abstract class FieldValue<T extends Field<U>, U> {

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
