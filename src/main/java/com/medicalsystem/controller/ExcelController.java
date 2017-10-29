package com.medicalsystem.controller;

import com.medicalsystem.service.ExcelService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ExcelController {

    private final ExcelService excelService;

    @PostMapping("api/import/excel")
    public ResponseEntity<?> importToDatabase(@RequestParam("file") MultipartFile file) {
        return excelService.importToDatabase(file);
    }

    @GetMapping("api/export")
    public ResponseEntity<?> exportToFile() {
        return excelService.exportToFile();
    }
}
