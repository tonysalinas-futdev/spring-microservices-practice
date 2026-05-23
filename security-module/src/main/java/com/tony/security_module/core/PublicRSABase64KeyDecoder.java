package com.tony.security_module.core;

import org.springframework.security.oauth2.jwt.JwtException;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class PublicRSABase64KeyDecoder {
    public static RSAPublicKey decode(String base64Key){
        byte[] decodedKey= Base64.getDecoder().decode(base64Key);
        X509EncodedKeySpec spec=new X509EncodedKeySpec(decodedKey);
        try {
            return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(spec);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new JwtException("Error jwt");
        }
    }
}
