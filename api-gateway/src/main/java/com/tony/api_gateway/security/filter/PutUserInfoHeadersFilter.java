package com.tony.api_gateway.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;


import reactor.core.publisher.Mono;

import java.util.List;
/*
@Component

public class PutUserInfoHeadersFilter  implements GlobalFilter {
    private static final Logger logger=LoggerFactory.getLogger(PutUserInfoHeadersFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain){
       return ReactiveSecurityContextHolder.getContext().map(ctx->ctx.getAuthentication()).flatMap(auth->
        {
            if (auth instanceof JwtAuthenticationToken jwt){
                Jwt jwtToken=jwt.getToken();
                String role=jwtToken.getClaim("role");
                String id=jwtToken.getClaim("id");
                String email=jwtToken.getSubject();
                List<String> permissions=jwtToken.getClaim("permissions");
                var mutatedExchange=exchange.mutate().request(exchange.getRequest().mutate()
                        .header("X-User-Id",id)
                        .header("X-User-Role", role)
                        .header("X-User-Permissions",String.join(",",permissions))
                        .header("X-User-Email",email)
                        .build()).build();
                logger.info("Info del usuario de la petición: id={}, role={}, email={}, permisos={}",id,role,email,permissions);
                return chain.filter(mutatedExchange);
            }
            return chain.filter(exchange);
        });

    }
}
*/