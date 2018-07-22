package com.hiper2d.repository

import com.hiper2d.model.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

const val TEMP_PASS = "\$2a\$14\$H4qy71pvJHnyTGpkHhY7dOwQgB492hVGNsOnggLgtMnUGZLN4EGeq" // password: Qwe123, base64: UXdlMTIz

@Repository
class UserRepository {

    private val me = User("hiper2d", TEMP_PASS, listOf("ME")) // password Qwe123

    fun findByUsername(username: String): Mono<UserDetails> {
        return Mono.just(username)
                .filter { it == me.username }
                .map { me }
    }
}