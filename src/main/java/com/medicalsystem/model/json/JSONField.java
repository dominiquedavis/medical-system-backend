package com.medicalsystem.model.json;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class JSONField {

    @Getter @Setter
    private long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String type;

    @Getter @Setter
    private List<?> values;

    @Getter @Setter
    private List<?> possibleValues;

}
