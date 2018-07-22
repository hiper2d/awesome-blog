package com.hiper2d.auth.filter

import com.hiper2d.auth.JwtConfigService
import com.hiper2d.auth.token.GuestAuthenticationToken
import com.hiper2d.auth.token.JwtPreAuthenticationToken
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureException
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

class JwtAuthenticationWebFilter(
        authenticationManager: ReactiveAuthenticationManager,
        private val jwtConfig: JwtConfigService
) : AuthenticationWebFilter(authenticationManager) {

    init {
        setAuthenticationConverter { convert(it) }
    }

    private fun convert(serverWebExchange: ServerWebExchange): Mono<Authentication?> {
        return extractJwtToken(serverWebExchange.request)
    }

    private fun extractJwtToken(request: ServerHttpRequest): Mono<Authentication?> {
        val bearerRequestHeader: String? = request.headers.getFirst(jwtConfig.tokenHeader)
        if (bearerRequestHeader != null && bearerRequestHeader.startsWith(jwtConfig.bearerPrefix)) {
            val token = bearerRequestHeader.substring(jwtConfig.bearerPrefix.length + 1)
            return try {
                val claims = Jwts.parser().setSigningKey(jwtConfig.secret).parseClaimsJws(token).body
                Mono.just(JwtPreAuthenticationToken(claims.subject, token))
            } catch (ex: SignatureException) {
                ex.printStackTrace()
                Mono.error(BadCredentialsException("Invalid token..."))
            }

        }
        return Mono.empty()
    }
}