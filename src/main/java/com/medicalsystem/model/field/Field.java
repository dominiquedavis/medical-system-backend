package com.medicalsystem.model.field;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Map;

@MappedSuperclass
public abstract class Field<T> {

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

    @Transient
    private Map<String, T> options;

    public Map<String, T> getOptions() {
        return options;
    }

    public void setOptions(Map<String, T> options) {
        this.options = options;
    }

    public void addOption(String key, T value) {
        options.put(key, value);
    }

}
