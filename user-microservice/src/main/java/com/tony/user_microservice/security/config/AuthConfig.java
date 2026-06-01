package com.tony.user_microservice.security.config;

import com.tony.user_microservice.model.User;
import com.tony.user_microservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor

public class AuthConfig {
        private final UserRepository repo;

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public UserDetailsService userdetailsService() {
            return username -> {
                User user =
                        repo.findByEmail(username)
                                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
                List<String> authorities = new ArrayList<>();

                authorities.addAll( user.getRoles().stream().map(role-> "ROLE_"+ role.getName()
                ).toList());

                user.getRoles().forEach(role->role.getPermissions().forEach(p->authorities.add(p.getName())));

                return org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .authorities(authorities.toArray(new String[0]))
                        .build();
            };
        }

        @Bean
        public AuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userdetailsService());
            provider.setPasswordEncoder(passwordEncoder());
            return provider;
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config){
            return config.getAuthenticationManager();
        }

    }


