package com.medicalsystem.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {

    ResponseEntity<?> importToDatabase(MultipartFile file);

    ResponseEntity<?> exportToFile();

}
