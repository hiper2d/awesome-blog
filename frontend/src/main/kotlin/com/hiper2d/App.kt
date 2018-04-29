package com.hiper2d

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
class App: SpringBootServletInitializer()

fun main(args: Array<String>) {
    SpringApplication.run(App::class.java, *args)
}
