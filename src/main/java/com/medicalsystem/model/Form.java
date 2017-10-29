package com.medicalsystem.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "FORMS")
@NoArgsConstructor
public class Form extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Getter @Setter
    private long id;

    @Column(name = "NAME")
    @Getter @Setter
    private String name;

    @Column(name = "TYPE")
    @Getter @Setter
    private String type;

    @Column(name = "EXCEL_SHEET_INDEX")
    @Getter @Setter
    private int excelSheetIndex;

    @OneToMany(
            mappedBy = "form",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Getter @Setter
    private List<Section> sections = new ArrayList<>();

}
