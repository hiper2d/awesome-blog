package com.hiper2d.handler

import com.hiper2d.auth.model.JwtAuthenticationRequest
import com.hiper2d.auth.model.JwtAuthenticationResponse
import com.hiper2d.jwt.JwtConfigService
import com.hiper2d.model.User
import com.hiper2d.repository.UserRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import javax.annotation.PostConstruct

@Component
class AuthHandler {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var jwtConfig: JwtConfigService

    @Autowired
    private lateinit var encoder: PasswordEncoder

    @CrossOrigin(origins = ["*"])
    fun authenticate(request: ServerRequest): Mono<ServerResponse> {

        val user = request.bodyToMono(JwtAuthenticationRequest::class.java)
                .flatMap { findUser(it) }
                .map { JwtAuthenticationResponse(it.username, generateJwtToken(it)) }

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(user, JwtAuthenticationResponse::class.java)
    }

    @CrossOrigin(origins = ["*"])
    fun validate(request: ServerRequest): Mono<ServerResponse> {

        val user = request.bodyToMono(JwtAuthenticationRequest::class.java)
                .flatMap { findUser(it) }
                .map { JwtAuthenticationResponse(it.username, generateJwtToken(it)) }

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(user, JwtAuthenticationResponse::class.java)
    }

    private fun findUser(token: JwtAuthenticationRequest) =
            userRepository.findByUsername(token.username)
                    .filter { encoder.matches(token.password, it.password) }
                    .switchIfEmpty(throwUserNotFoundException())

    private fun throwUserNotFoundException(): Mono<User> =
            Mono.error(RuntimeException("Invalid credentials"))

    private fun generateJwtToken(it: User): String =
            Jwts.builder()
                    .setSubject(it.username)
                    .signWith(SignatureAlgorithm.HS512, jwtConfig.secret)
                    .compact()
}