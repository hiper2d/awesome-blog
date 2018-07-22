package com.hiper2d.auth

import org.springframework.beans.factory.annotation.Value

class JwtConfigService {

    @Value("\${jwt.header}")
    private var _tokenHeader: String = ""
    val tokenHeader: String
        get() = _tokenHeader

    @Value("\${jwt.param}")
    private var _tokenParam: String = ""
    val tokenParam: String
        get() = _tokenParam

    @Value("\${jwt.prefix}")
    private var _bearerPrefix: String = ""
    val bearerPrefix: String
        get() = _bearerPrefix

    @Value("\${jwt.secret}")
    private var _secret: String = ""
    val secret: String
        get() = _secret
}