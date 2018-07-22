package com.hiper2d.repository

import com.hiper2d.model.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class UserRepository {

    private val me = User("hiper2d", "Qwe123", listOf("ME"))

    fun findByUsername(username: String): Mono<UserDetails> {
        return Mono.just(username)
                .filter { it == me.username }
                .map { me }
    }
}