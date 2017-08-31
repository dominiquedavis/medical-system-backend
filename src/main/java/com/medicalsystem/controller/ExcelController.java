package com.medicalsystem.controller;

import com.medicalsystem.excel.importer.Importer;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@Log
public class ExcelController {

    private final String FILE_NAME = "baza2.xlsx";

    private final Importer importer;
    @Autowired
    public ExcelController(Importer importer) {
        this.importer = importer;
    }


    @GetMapping("/import")
    public ResponseEntity<?> importToDB() {
        File file = new File(FILE_NAME);
        try {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /* Import data to db */
        importer.importToDB(FILE_NAME);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
