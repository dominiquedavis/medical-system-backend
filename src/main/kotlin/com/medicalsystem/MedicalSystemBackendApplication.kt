package com.medicalsystem

import com.medicalsystem.domain.ApplicationUser
import com.medicalsystem.excel.importer.ExcelImporter
import com.medicalsystem.init.TemplateInitializer
import com.medicalsystem.service.ApplicationUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class MedicalSystemBackendApplication : ApplicationRunner {

    @Autowired
    private lateinit var templateInitializer: TemplateInitializer

    @Autowired
    private lateinit var excelImporter: ExcelImporter

    @Autowired
    private lateinit var userService: ApplicationUserService

    override fun run(agrs: ApplicationArguments?) {
        templateInitializer.loadTemplateFromConfig()
        excelImporter.importToDatabase("data/baza2_test.xlsx")

        userService.register(ApplicationUser(
                fullName = "Administrator systemu", username = "admin", email = "administrator@medicalsystem.net",
                password = "admin", admin = true, status = "Aktywny"))

        userService.register(ApplicationUser(
                fullName = "Mikołaj Sieniawski", username = "msieniawski", email = "msieniawski@gmail.com",
                password = "msieniawski", admin = true, status = "Aktywny"))

        userService.register(ApplicationUser(
                fullName = "Tomasz Radwan", username = "tradwan", email = "trwadwan@gmail.com",
                password = "tradwan", admin = false, status = "Aktywny"))

        userService.register(ApplicationUser(
                fullName = "Jakub Piekarz", username = "jpiekarz", email = "jpiekarz@gmail.com",
                password = "jpiekarz", admin = false, status = "Aktywny"))
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}

fun main(args: Array<String>) {
    SpringApplication.run(MedicalSystemBackendApplication::class.java, *args)
}
