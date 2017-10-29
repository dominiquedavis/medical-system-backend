package com.medicalsystem.model.json;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class JSONForm {

    @Getter @Setter
    private long id;

    @Getter @Setter
    private String type;

    @Getter @Setter
    private List<JSONSection> sections;

}
