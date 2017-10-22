package com.medicalsystem.json.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class JSONField {

    @Getter @Setter
    private int id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String type;

    @Getter @Setter
    private List<?> values;

    @Getter @Setter
    private List<?> possibleValues;

}
