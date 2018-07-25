package com.hiper2d.handler

import com.hiper2d.auth.JwtConfigService
import com.hiper2d.auth.JwtPasswordEncoder
import com.hiper2d.auth.model.JwtAuthenticationRequest
import com.hiper2d.auth.model.JwtAuthenticationResponse
import com.hiper2d.repository.UserRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.MediaType
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class AuthHandler(
        private val userRepository: UserRepository,
        private val jwtConfig: JwtConfigService,
        private val encoder: PasswordEncoder
) {

    @CrossOrigin(origins = ["*"])
    fun authenticate(request: ServerRequest): Mono<ServerResponse> {

        val user = request.bodyToMono(JwtAuthenticationRequest::class.java)
                .flatMap { findUser(it) }
                .map { JwtAuthenticationResponse(it.username, generateJwtToken(it)) }

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(user, JwtAuthenticationResponse::class.java)
    }

    private fun findUser(token: JwtAuthenticationRequest) =
            userRepository.findByUsername(token.username)
                    .filter { encoder.matches(token.password, it.password) } //todo: doesn't work, need to learn BCrypt and fix tomorrow
                    .switchIfEmpty(throwUserNotFoundException())

    private fun throwUserNotFoundException(): Mono<UserDetails> =
            Mono.error(RuntimeException("Invalid credentials"))

    private fun generateJwtToken(it: UserDetails): String =
            Jwts.builder()
                    .setSubject(it.username)
                    .signWith(SignatureAlgorithm.HS512, jwtConfig.secret)
                    .compact()
}