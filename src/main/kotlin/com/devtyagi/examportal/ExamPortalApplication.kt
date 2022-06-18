package com.devtyagi.examportal

import com.devtyagi.examportal.service.AdminService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExamPortalApplication(
    private val adminService: AdminService
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        if(adminService.doesAdminExist()) return
        adminService.createAdmin()
    }

}

fun main(args: Array<String>) {
    runApplication<ExamPortalApplication>(*args)
}
