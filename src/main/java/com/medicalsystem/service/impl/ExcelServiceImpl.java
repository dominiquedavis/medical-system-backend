package com.medicalsystem.service.impl;

import com.medicalsystem.excel.exporter.ExcelExporter;
import com.medicalsystem.excel.importer.ExcelImporter;
import com.medicalsystem.service.ExcelService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class ExcelServiceImpl implements ExcelService {

    private final String IMPORT_FILE_NAME = "data/dataImport.xlsx";
    private final String EXPORT_FILE_NAME = "data/dataExport.xlsx";

    private final ExcelExporter exporter;
    private final ExcelImporter importer;

    @Override
    public ResponseEntity<?> exportToFile() {
        ResponseEntity<?> response;
        try {
            exporter.exportToFile(EXPORT_FILE_NAME);
            File file = new File(EXPORT_FILE_NAME);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            response = ResponseEntity.ok()
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        } catch (IOException e) {
            log.severe(e.getMessage());
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @Override
    public ResponseEntity<?> importToDatabase(MultipartFile file) {
        ResponseEntity<?> response;
        try {
            saveFile(file);
            importer.importToDatabase(IMPORT_FILE_NAME);
            response = new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            log.severe(e.getMessage());
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    private void saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("The file is empty");
        }

        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(IMPORT_FILE_NAME)))) {
            byte[] bytes = file.getBytes();
            stream.write(bytes);
            log.info("File uploaded: " + IMPORT_FILE_NAME);
        }
    }
}
