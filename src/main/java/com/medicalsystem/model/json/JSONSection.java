package com.medicalsystem.model.json;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class JSONSection {

    @Getter @Setter
    private long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private List<JSONField> fields;

}
