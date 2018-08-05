package com.hiper2d.handler

import com.hiper2d.BaseWebTest
import com.hiper2d.auth.model.JwtAuthenticationRequest
import com.hiper2d.auth.model.JwtAuthenticationResponse
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.BodyInserters
import reactor.core.publisher.Mono


class AuthHandlerTest: BaseWebTest() {

    @Test
    fun authenticate() {
        val req = JwtAuthenticationRequest("hiper2d", "Qwe123")
        webClient.post().uri("token")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromPublisher(Mono.just(req), JwtAuthenticationRequest::class.java))
                .exchange()
                .expectStatus().isOk
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(JwtAuthenticationResponse::class.java)
    }

    @Test
    fun validate() {
    }
}