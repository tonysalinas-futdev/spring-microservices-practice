package com.tony.security_module.core.standard;

import com.tony.security_module.core.JwtProperties;
import com.tony.security_module.core.PublicRSABase64KeyDecoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;
@Profile("!test")
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class JwtConfig {

    private final JwtProperties properties;

    public JwtConfig(JwtProperties properties) {
        this.properties = properties;
    }
    @Bean
    public JwtDecoder jwtDecoder(){
        RSAPublicKey publicKey = PublicRSABase64KeyDecoder.decode(properties.getPublicKey());
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
        
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter roleConverter=new JwtGrantedAuthoritiesConverter();
        roleConverter.setAuthoritiesClaimName("roles");
        roleConverter.setAuthorityPrefix("ROLE_");

        JwtGrantedAuthoritiesConverter permissionConverter=new JwtGrantedAuthoritiesConverter();
        permissionConverter.setAuthoritiesClaimName("permissions");
        permissionConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter converter=new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt->{
            var roles=roleConverter.convert(jwt);
            var permissions=permissionConverter.convert(jwt);
            List<GrantedAuthority> permissionsAndRole=new ArrayList<>();
            permissionsAndRole.addAll(roles);
            permissionsAndRole.addAll(permissions);
            return  permissionsAndRole;
        });
        return converter;
    }
}
