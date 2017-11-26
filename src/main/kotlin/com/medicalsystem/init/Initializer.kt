package com.medicalsystem.init

import com.medicalsystem.model.ApplicationUser
import com.medicalsystem.model.Form
import com.medicalsystem.properties.FromPropertiesModelFactory
import com.medicalsystem.service.ApplicationUserService
import com.medicalsystem.service.ExcelService
import com.medicalsystem.service.FormService
import com.medicalsystem.util.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class Initializer @Autowired constructor(
        val formService: FormService,
        val userService: ApplicationUserService,
        val excelService: ExcelService,
        val formPropertiesModelFactory: FromPropertiesModelFactory) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        /*
        userService.register(ApplicationUser(username = "admin", password = "admin", admin = true))
        runInitialFormConfiguration()
        excelService.importToDatabase("data/baza2_test.xlsx")
        excelService.exportToFile("data/exported.xlsx")
        */
    }

    fun runInitialFormConfiguration() {
        val forms: List<Form> = formPropertiesModelFactory.createForms()
        forms.forEach { form ->
            formService.save(form)
            logger().info("Form saved: $form")
        }
    }
}