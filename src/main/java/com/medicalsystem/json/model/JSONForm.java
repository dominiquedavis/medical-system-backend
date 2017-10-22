package com.medicalsystem.json.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class JSONForm {

    @Getter @Setter
    private int id;

    @Getter @Setter
    private String type;

    @Getter @Setter
    private List<JSONSection> sections;

}
