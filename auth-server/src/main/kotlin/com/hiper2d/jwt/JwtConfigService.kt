package com.hiper2d.jwt

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JwtConfigService {

    @Value("\${jwt.header}")
    private lateinit var _tokenHeaderName: String
    val tokenHeaderName: String
        get() = _tokenHeaderName

    @Value("\${jwt.param}")
    private lateinit var _tokenParam: String
    val tokenParam: String
        get() = _tokenParam

    @Value("\${jwt.prefix}")
    private lateinit var _tokePrefix: String
    val bearerPrefix: String
        get() = _tokePrefix

    @Value("\${jwt.secret}")
    private lateinit var _secret: String
    val secret: String
        get() = _secret
}