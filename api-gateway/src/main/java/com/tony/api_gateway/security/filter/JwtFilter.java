package com.tony.api_gateway.security.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.List;

@Component
public class JwtFilter implements GlobalFilter{

    @Override
    public Mono<Void>filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain
    ){ return exchange.getPrincipal()
            .cast(JwtAuthenticationToken.class)
            .flatMap(jwtAuth -> {

                Jwt jwt = jwtAuth.getToken();

                String userEmail = jwt.getSubject();
                String userId = jwt.getClaim("id");
                String role = jwt.getClaim("role");

                List<String> permissions =
                        jwt.getClaimAsStringList("permissions");

                ServerHttpRequest changedRequest =
                        exchange.getRequest()
                                .mutate()
                                .header("X-User-Id", userId)
                                .header("X-UserEmail", userEmail)
                                .header("X-UserRole", role)
                                .header(
                                        "X-Permissions",
                                        String.join(",", permissions)
                                )
                                .build();

                ServerWebExchange newExchange =
                        exchange.mutate()
                                .request(changedRequest)
                                .build();

                return chain.filter(newExchange);
            })
            .switchIfEmpty(chain.filter(exchange));
    }
}
