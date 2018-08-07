package com.hiper2d.config

import com.hiper2d.handler.AuthHandler
import com.hiper2d.handler.EchoHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router


@Configuration
class WebFluxRouter {

    @Bean
    fun router(echoHandler: EchoHandler, authHandler: AuthHandler) = router {
        GET("/echo").nest {
            accept(MediaType.TEXT_HTML, echoHandler::sayHi)
        }
        POST("/token").nest {
            accept(MediaType.APPLICATION_JSON_UTF8, authHandler::authenticate)
        }
    }
}
