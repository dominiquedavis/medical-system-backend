package com.medicalsystem.controller;

import com.medicalsystem.model.StringField;
import com.medicalsystem.service.StringFieldService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StringFieldController {

    private final StringFieldService stringFieldService;

    @GetMapping("api/stringFields")
    public List<StringField> getAllStringFields() {
        return stringFieldService.findAll();
    }

}
