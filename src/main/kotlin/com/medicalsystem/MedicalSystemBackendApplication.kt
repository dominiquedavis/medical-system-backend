package com.medicalsystem

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class MedicalSystemBackendApplication : ApplicationRunner {
    /**
     * Executes at the application start
     */
    override fun run(args: ApplicationArguments?) {
    }
}


fun main(args: Array<String>) {
    SpringApplication.run(MedicalSystemBackendApplication::class.java, *args)
}
