package com.medicalsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "FIELDS")
@NoArgsConstructor
public class Field extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "ID")
    @Getter @Setter
    private long id;

    @Column(name = "NAME")
    @Getter @Setter
    private String name;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private FieldType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SECTION_ID")
    @Getter @Setter
    private Section section;

    @ElementCollection
    @CollectionTable(name = "FORM_COLUMN_INDICES")
    @MapKeyJoinColumn(name = "FORM_ID")
    @Column(name = "COLUMN_INDEX")
    @Getter @Setter
    private Map<Form, Integer> indices = new HashMap<>();

    @ElementCollection
    @CollectionTable(
            name = "POSSIBLE_VALUES",
            joinColumns = @JoinColumn(name = "FIELD_ID")
    )
    @MapKeyColumn(name = "KEY")
    @Column(name = "VALUE")
    @Getter @Setter
    private Map<String, String> possibleValues = new HashMap<>();

}
