package com.hiper2d.config

import com.hiper2d.handler.AuthHandler
import com.hiper2d.handler.EchoHandler
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

    @Autowired
    private lateinit var echoHandler: EchoHandler

    @Bean
    fun authRouter() = router {
        GET("/echo").nest {
            accept(MediaType.TEXT_HTML, echoHandler::echo)
        }
        GET("/validate").nest {
            accept(MediaType.APPLICATION_JSON_UTF8, authHandler::validate)
        }
        POST("/token").nest {
            accept(MediaType.APPLICATION_JSON_UTF8, authHandler::authenticate)
        }
    }
}
