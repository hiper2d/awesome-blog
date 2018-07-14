package com.hiper2d.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    @Bean
    override fun authenticationManager(): AuthenticationManager = super.authenticationManager()

    @Bean
    override fun userDetailsService(): UserDetailsService = super.userDetailsService()

    // Use no encoding provider for test purposes
    @Bean
    fun passwordEncoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()

    override fun configure(http: HttpSecurity) {
        http
                .httpBasic().disable()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("hiper2d")
                    .password("Qwe123")
                    .roles("USER", "ADMIN")
                .and()
                .withUser("tech")
                    .password("tech")
                    .roles("USER")
    }
}