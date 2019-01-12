package com.hiper2d.auth.filter

import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilterChain
import org.springframework.web.server.adapter.DefaultServerWebExchange
import reactor.core.publisher.Mono

class JwtAuthenticationWebFilter(
        authenticationManager: ReactiveAuthenticationManager,
        authenticationTokenConverter: ServerAuthenticationConverter
) : AuthenticationWebFilter(authenticationManager) {

    init {
        setServerAuthenticationConverter(authenticationTokenConverter)
    }

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        /*
            For some reason all AuthenticationWebFilter implementations are called twice.
            In each call the exchange context is different:
            - DefaultServerWebExchange from Spring Web module,
            - SecurityContextServerWebExchange from Spring Security module.
            I filter them and keep only one call for DefaultServerWebExchange.
        */
        return  if (exchange is DefaultServerWebExchange)
            super.filter(exchange, chain)
        else
            chain.filter(exchange)
    }
}
