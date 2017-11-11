package com.medicalsystem

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

fun main(args: Array<String>) {
    SpringApplication.run(MedicalSystemBackendApplication::class.java, *args)
}

@SpringBootApplication
class MedicalSystemBackendApplication {

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}



