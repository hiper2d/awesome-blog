package com.hiper2d.auth

import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.security.SecureRandom
import java.util.*

class JwtPasswordEncoder: PasswordEncoder {

    override fun encode(rawPassword: CharSequence): String {
        val random = SecureRandom()
        val bytes = ByteArray(20)
        random.nextBytes(bytes)

        return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(14, random))
    }

    override fun matches(base64Password: CharSequence, encodedPassword: String): Boolean {
        val rawPassword = String(Base64.getDecoder().decode(base64Password.toString()))
        return BCrypt.checkpw(rawPassword, encodedPassword)
    }
}