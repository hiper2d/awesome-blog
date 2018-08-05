package com.hiper2d.config

import com.hiper2d.handler.AuthHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.function.server.router


@Configuration
class WebFluxConfig: WebFluxConfigurer {

    @Autowired
    private lateinit var authHandler: AuthHandler

    @Bean
    fun authRouter() = router {
        GET("/validate").nest {
            accept(MediaType.APPLICATION_JSON_UTF8, authHandler::validate)
        }
        POST("/token").nest {
            accept(MediaType.APPLICATION_JSON_UTF8, authHandler::authenticate)
        }
    }
}
