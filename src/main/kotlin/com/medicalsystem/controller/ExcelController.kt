package com.medicalsystem.controller

import com.medicalsystem.service.ExcelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class ExcelController @Autowired constructor(val excelService: ExcelService) {

    @PostMapping
    fun importToDatabase(@RequestParam("file") file: MultipartFile): ResponseEntity<*>
            = excelService.importToDatabase(file)
}