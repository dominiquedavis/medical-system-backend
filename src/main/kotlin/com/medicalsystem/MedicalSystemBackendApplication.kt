package com.medicalsystem

import com.medicalsystem.init.Initializer
import com.medicalsystem.service.ExcelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

fun main(args: Array<String>) {
    SpringApplication.run(MedicalSystemBackendApplication::class.java, *args)
}

@SpringBootApplication
class MedicalSystemBackendApplication @Autowired constructor(
        var initializer: Initializer,
        var excelService: ExcelService) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        initializer.runInitialConfiguration()
        excelService.importToDatabase("data/baza2.xlsx")
    }
}



