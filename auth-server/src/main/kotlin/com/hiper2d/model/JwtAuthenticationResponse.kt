package com.hiper2d.auth.model

import java.io.Serializable

class JwtAuthenticationResponse(val username: String, val token: String): Serializable