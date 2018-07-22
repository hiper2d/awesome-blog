package com.hiper2d.auth.token

import org.springframework.security.authentication.AbstractAuthenticationToken
import javax.security.auth.Subject

class JwtPreAuthenticationToken(
        val username: String,
        val authToken: String
): AbstractAuthenticationToken(null) {

    init {
        isAuthenticated = false
    }

    override fun getCredentials(): Any? = null

    override fun getPrincipal(): Any? = null

    override fun implies(subject: Subject?) = false
}