package com.medicalsystem.controller;

import com.medicalsystem.excel.exporter.DataExporter;
import com.medicalsystem.excel.importer.DataImporter;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class ExcelController {

    private final String IMPORT_FILE_NAME = "data/dataImport.xlsx";
    private final String EXPORT_FILE_NAME = "data/dataExport.xlsx";

    private final DataImporter dataImporter;
    private final DataExporter dataExporter;

    @PostMapping("api/import/excel")
    public ResponseEntity<?> importToDB(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty())
            return new ResponseEntity<>("Error uploading file", HttpStatus.BAD_REQUEST);

        /* Save file */
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(IMPORT_FILE_NAME)))) {
            byte[] bytes = file.getBytes();
            stream.write(bytes);
            log.info("File uploaded: " + IMPORT_FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dataImporter.importToDatabase(new FileInputStream(IMPORT_FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("api/export")
    public ResponseEntity<?> exportToExcel() throws IOException {
        dataExporter.exportToFile(EXPORT_FILE_NAME);
        File file = new File(EXPORT_FILE_NAME);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

}
