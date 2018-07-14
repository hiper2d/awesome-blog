package com.hiper2d.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

@Configuration
class TokenStoreConfig {

    @Value("\${security.oauth2.resource.jwt.key-value}")
    val jwtKey = ""

    @Bean
    fun tokenService(): DefaultTokenServices =
            DefaultTokenServices().apply {
                setTokenStore(tokenStore())
                setSupportRefreshToken(true)
            }

    @Bean
    fun tokenStore(): TokenStore = JwtTokenStore(jwtAccessTokenConverter())

    @Bean
    fun jwtAccessTokenConverter(): JwtAccessTokenConverter = JwtAccessTokenConverter().also {
        it.setSigningKey(jwtKey)
    }
}