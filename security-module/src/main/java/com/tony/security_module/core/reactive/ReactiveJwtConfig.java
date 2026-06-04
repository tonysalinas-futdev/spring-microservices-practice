package com.tony.security_module.core.reactive;


import com.tony.security_module.shared.JwtProperties;
import com.tony.security_module.shared.PublicRSABase64KeyDecoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;

import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)

public class ReactiveJwtConfig {

    private final JwtProperties properties;

    public ReactiveJwtConfig(JwtProperties properties) {
        this.properties = properties;
    }

    @Bean
    public ReactiveJwtDecoder reactivejwtDecoder(){
        RSAPublicKey publicKey = PublicRSABase64KeyDecoder.decode(properties.getPublicKey());
        return NimbusReactiveJwtDecoder.withPublicKey(publicKey).build();

    }
    @Bean
    public ReactiveJwtAuthenticationConverterAdapter reactiveJwtAuthenticationConverterAdapter(){
        JwtAuthenticationConverter converter=new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt->{
            List<String> roles=jwt.getClaim("roles");
            List<String> permissions=jwt.getClaim("permissions");
            List<GrantedAuthority> authorities=new ArrayList<>();
            roles.forEach(r->authorities.add(new SimpleGrantedAuthority("ROLE_"+r)));
            permissions.forEach(p->authorities.add(new SimpleGrantedAuthority(p)));
            return authorities;
        });
        return new ReactiveJwtAuthenticationConverterAdapter(converter);
    }
}
