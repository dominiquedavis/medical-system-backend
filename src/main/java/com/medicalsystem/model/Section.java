package com.medicalsystem.model;

import com.medicalsystem.model.field.Field;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SECTIONS")
public class Section {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter @Setter
    private int id;

    @Column(name = "name")
    @Getter @Setter
    private String name;

    @ManyToMany(mappedBy = "sections", fetch = FetchType.EAGER)
    @Getter @Setter
    private List<Field<?>> fields;

}
