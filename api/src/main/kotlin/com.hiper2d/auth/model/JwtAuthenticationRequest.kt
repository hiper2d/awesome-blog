package com.hiper2d.auth.model

import java.io.Serializable

class JwtAuthenticationRequest(val username: String, val password: String): Serializable