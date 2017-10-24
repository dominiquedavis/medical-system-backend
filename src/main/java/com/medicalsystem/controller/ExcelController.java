package com.medicalsystem.controller;

import com.medicalsystem.excel.importer.DataImporter;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class ExcelController {

    private final String FILE_NAME = "data/data.xlsx";

    private final DataImporter dataImporter;

    @PostMapping("api/import/excel")
    public ResponseEntity<?> importToDB(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty())
            return new ResponseEntity<>("Error uploading file", HttpStatus.BAD_REQUEST);

        /* Save file */
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(FILE_NAME)))) {
            byte[] bytes = file.getBytes();
            stream.write(bytes);
            log.info("File uploaded: " + FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dataImporter.importToDatabase(new FileInputStream(FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
