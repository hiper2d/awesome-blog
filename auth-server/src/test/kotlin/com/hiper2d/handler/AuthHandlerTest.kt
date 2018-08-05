package com.hiper2d.handler

import com.hiper2d.BaseWebTest
import org.junit.jupiter.api.Test


class AuthHandlerTest: BaseWebTest() {

    @Test
    fun authenticate() {
        // val req = JwtAuthenticationRequest("hiper2d", "Qwe123")
        webClient.get().uri("echo")
                .exchange()
                .expectStatus().isOk
    }

    @Test
    fun validate() {
    }
}