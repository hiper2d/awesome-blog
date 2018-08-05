package com.hiper2d.repository

import com.hiper2d.model.User
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import javax.annotation.PostConstruct

const val TEMP_PASS = "\$2a\$14\$qum0p6xapj.hNzJiZPOod.8iQ/tvsZhcnk6qTTS704ptZpdpIfgMW" // password: Qwe123

@Repository
class UserRepository {

    private val me = User("hiper2d", TEMP_PASS, listOf("ME")) // password Qwe123

    @PostConstruct
    fun init() {
        println("UserRepository bean was initialized")
    }

    fun findByUsername(username: String): Mono<User> {
        return Mono.just(username)
                .filter { it == me.username }
                .map { me }
    }
}