package com.hiper2d.controller

import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class EchoController {

    @GetMapping("echo")
    fun echo(token: OAuth2Authentication) = Mono.just("echo")
}