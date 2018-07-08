package com.hiper2d.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer

@Configuration
@EnableAuthorizationServer
class OAuth2Config @Autowired constructor(
        private val authenticationManager: AuthenticationManager,
        private val userDetailsService: UserDetailsService
): AuthorizationServerConfigurerAdapter() {

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients
                .inMemory()
                .withClient("frontend")
                .secret("awesome_secret")
                .authorizedGrantTypes(
                        "refresh_token",
                        "password",
                        "client_credentials"
                )
                .scopes("webclient")
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
    }
}
