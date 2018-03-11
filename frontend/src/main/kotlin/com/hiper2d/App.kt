package com.hiper2d

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
class App: SpringBootServletInitializer()

fun main(args: Array<String>) {
    SpringApplication.run(App::class.java, *args)
}
