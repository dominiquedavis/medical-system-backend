package com.medicalsystem.service

interface ExcelService {
    fun importToDatabase(path: String)
    fun exportToFile(path: String)
}