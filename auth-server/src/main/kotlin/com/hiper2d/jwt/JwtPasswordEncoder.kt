package com.hiper2d.jwt

import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.security.SecureRandom
import java.util.*

@Component
class JwtPasswordEncoder: PasswordEncoder {

    override fun encode(rawPassword: CharSequence): String {
        val random = SecureRandom()
        val bytes = ByteArray(20)
        random.nextBytes(bytes)

        return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(14, random))
    }

    override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {
        return BCrypt.checkpw(rawPassword.toString(), encodedPassword)
    }
}