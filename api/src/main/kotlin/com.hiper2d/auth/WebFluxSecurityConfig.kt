package com.hiper2d.auth

import com.hiper2d.auth.filter.GuestAuthenticationWebFilter
import com.hiper2d.auth.filter.JwtAuthenticationWebFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.server.SecurityWebFilterChain


@Configuration
@EnableWebFluxSecurity
class WebFluxSecurityConfig {

    @Bean fun jwtConfig() = JwtConfigService()

    @Bean fun authenticationManager() = JwtReactiveAuthenticationManager(userDetailsService())

    @Bean fun guestAuthenticationWebFilter() = GuestAuthenticationWebFilter(authenticationManager())

    @Bean fun jwtAuthenticationWebFilter() = JwtAuthenticationWebFilter(authenticationManager(), jwtConfig())

    @Bean
    fun userDetailsService(): MapReactiveUserDetailsService {
        val me = User.builder()
                .username("hiper2d")
                .password("Qwe123")
                .authorities("ME")
                .build()
        return MapReactiveUserDetailsService(me)
    }

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http.authorizeExchange()
                .anyExchange().authenticated()
                .and()
                .addFilterAt(jwtAuthenticationWebFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
                .addFilterAt(guestAuthenticationWebFilter(), SecurityWebFiltersOrder.SECURITY_CONTEXT_SERVER_WEB_EXCHANGE)
                .formLogin().disable()
                .httpBasic().disable()
                .build()
    }
}