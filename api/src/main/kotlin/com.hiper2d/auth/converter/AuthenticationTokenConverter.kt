package com.hiper2d.auth.converter

import com.hiper2d.auth.JwtConfigService
import com.hiper2d.auth.token.GuestAuthenticationToken
import com.hiper2d.auth.token.JwtPreAuthenticationToken
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureException
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.function.Function

class AuthenticationTokenConverter(
        private val jwtConfig: JwtConfigService
): Function<ServerWebExchange, Mono<Authentication>> {

    override fun apply(serverWebExchange: ServerWebExchange): Mono<Authentication> =
            Mono.just(serverWebExchange.request)
                    .flatMap(this::extractJwtToken)
                    .switchIfEmpty(Mono.just(GuestAuthenticationToken()))

    private fun extractJwtToken(request: ServerHttpRequest): Mono<Authentication> {
        val tokenHeader = request.geTokenHeader()
        if (tokenHeader.isToken()) {
            val token = tokenHeader!!.substring(jwtConfig.bearerPrefix.length + 1)

            return try {
                val claims = Jwts.parser().setSigningKey(jwtConfig.secret).parseClaimsJws(token).body
                Mono.just(JwtPreAuthenticationToken(claims.subject, token) as Authentication)
            } catch (ex: SignatureException) {
                ex.printStackTrace()
                Mono.error(BadCredentialsException("Invalid token..."))
            }
        }
        return Mono.empty<Authentication>()
    }

    private fun ServerHttpRequest.geTokenHeader(): String? = this.headers.getFirst(jwtConfig.tokenHeader)

    private fun String?.isToken() = this != null && this.startsWith(jwtConfig.bearerPrefix)
}