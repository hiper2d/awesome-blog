package com.hiper2d.handler

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

@Component
class EchoHandler {

    fun sayHi(req: ServerRequest) = ok()
            .contentType(MediaType.TEXT_HTML)
            .body(Mono.just("Hey"), String::class.java)
}