package com.tony.security_module.core.reactive;

import com.tony.security_module.shared.PublicRoutes;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)

public class ReactiveSecurityConfig{
@Bean
public SecurityWebFilterChain publicChain(ServerHttpSecurity http, ReactiveJwtAuthenticationConverterAdapter converter) {
    return http

            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
            .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
            .logout(ServerHttpSecurity.LogoutSpec::disable)

            .authorizeExchange(ex -> ex.pathMatchers(PublicRoutes.AUTH, PublicRoutes.ACTUATOR,                        PublicRoutes.SWAGGER_API_DOCS,
                    PublicRoutes.SWAGGER_RESOURCES,
                    PublicRoutes.SWAGGER_UI,
                    PublicRoutes.SWAGGER_UI_HTML,
                    PublicRoutes.WEBJARS).permitAll().anyExchange().authenticated())
            .oauth2ResourceServer(oauth -> oauth.jwt(jwt->jwt.jwtAuthenticationConverter(converter))).build();
}

}

