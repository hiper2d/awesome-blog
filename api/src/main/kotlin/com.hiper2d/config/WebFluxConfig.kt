package com.hiper2d.config

import com.hiper2d.handler.EchoHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.function.server.router


@Configuration
class WebFluxConfig: WebFluxConfigurer {

    @Bean
    fun echoRouter(echoHandler: EchoHandler) = router {
        GET("/api/echo").nest {
            accept(MediaType.TEXT_HTML, echoHandler::sayHi)
        }
    }
}
