package com.hiper2d.controller

import org.springframework.http.MediaType
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @GetMapping(value = ["user"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUser(token: OAuth2Authentication) =
        hashMapOf<String, Any?>(
                "user" to token.userAuthentication.principal,
                "authorities" to token.userAuthentication.authorities
        )
}