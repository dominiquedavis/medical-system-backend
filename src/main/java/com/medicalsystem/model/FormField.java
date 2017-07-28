package com.medicalsystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Map;

@MappedSuperclass
public abstract class FormField<K, V> {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter @Setter
    private int id;

    @Column(name = "name")
    @Getter @Setter
    private String name;

    @Column(name = "excel_column")
    @Getter @Setter
    private int excelColumn;

    public abstract Map<K, V> getPossibleValues();

    public abstract void setPossibleValues(Map<K, V> values);

    public abstract void addPossibleValue(K key, V value);

}
