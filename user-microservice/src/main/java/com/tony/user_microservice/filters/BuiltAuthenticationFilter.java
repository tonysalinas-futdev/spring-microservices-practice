package com.tony.user_microservice.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
@Component
@Slf4j
public class BuiltAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException,ServletException {
        if (request.getHeader("X-User-Id")==null){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
           return;
        }
        String userId=request.getHeader("X-User-Id");
        String userEmail=request.getHeader("X-User-Email");
        String role=request.getHeader("X-User-Role");
        List<String>permissions= Arrays.asList(request.getHeader("X-User-Permissions").split(","));

        List<GrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
        permissions.forEach(p->authorities.add(new SimpleGrantedAuthority(p)));

        Authentication authentication=new UsernamePasswordAuthenticationToken(userId,null,authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("Creado Authentication a usuario con id={}, email={} y rol={}",userId,userEmail,role);
        chain.doFilter(request,response);
    }
}
*/