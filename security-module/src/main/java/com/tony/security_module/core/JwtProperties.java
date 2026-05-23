package com.tony.security_module.core;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {


    private String publicKey;
    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String secret) {
        this.publicKey = secret;
    }

}
