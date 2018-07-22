package com.hiper2d.auth.filter

import com.hiper2d.auth.token.GuestAuthenticationToken
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import reactor.core.publisher.Mono

class GuestAuthenticationWebFilter(authenticationManager: ReactiveAuthenticationManager) : AuthenticationWebFilter(authenticationManager) {

    init {
        setAuthenticationConverter { convert() }
    }

    private fun convert(): Mono<Authentication> {
        return Mono.just(GuestAuthenticationToken())
    }
}