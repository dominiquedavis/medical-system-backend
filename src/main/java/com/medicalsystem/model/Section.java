package com.medicalsystem.model;

import com.medicalsystem.model.field.Field;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SECTIONS")
public class Section extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter @Setter
    private int id;

    @Column(name = "name")
    @Getter @Setter
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "FORM_SECTION")
    @Getter @Setter
    private List<Form> forms = new ArrayList<>();

    @ManyToMany(mappedBy = "sections", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @Getter @Setter
    private List<Field<?>> fields = new ArrayList<>();

    public void addField(Field<?> field) {
        fields.add(field);
    }

}
