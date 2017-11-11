package com.medicalsystem.service

import org.springframework.http.ResponseEntity
import org.springframework.web.multipart.MultipartFile

interface ExcelService {
    fun importToDatabase(file: MultipartFile): ResponseEntity<*>
    fun exportToFile(): ResponseEntity<*>

    fun importToDatabase(path: String)
    fun exportToFile(path: String)
}