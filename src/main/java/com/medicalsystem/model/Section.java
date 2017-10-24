package com.medicalsystem.model;

import com.medicalsystem.model.field.Field;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SECTIONS")
@NoArgsConstructor
public class Section extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;

    @Getter @Setter
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @Getter @Setter
    private Form form;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "SECTION_FIELD")
    @Getter @Setter
    private List<Field> fields = new ArrayList<>();

}
