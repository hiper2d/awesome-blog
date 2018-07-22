package com.hiper2d.handler

import com.hiper2d.auth.JwtConfigService
import com.hiper2d.auth.model.JwtAuthenticationRequest
import com.hiper2d.auth.model.JwtAuthenticationResponse
import com.hiper2d.repository.UserRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class AuthHandler(private val userRepository: UserRepository, private val jwtConfig: JwtConfigService) {

    @CrossOrigin(origins = ["*"])
    fun authenticate(request: ServerRequest): Mono<ServerResponse> {

        val user = request.bodyToMono(JwtAuthenticationRequest::class.java)
                .flatMap { userRepository.findByUsername(it.username) }
                .map { JwtAuthenticationResponse(it.username, Jwts.builder().setSubject(it.username).signWith(SignatureAlgorithm.HS512, jwtConfig.secret).compact()) }

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(user, JwtAuthenticationResponse::class.java)
    }
}