package com.hiper2d.auth.token

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class JwtAuthenticationToken(
        val username: String,
        authorities: Collection<GrantedAuthority>
): UsernamePasswordAuthenticationToken(username, null, authorities)