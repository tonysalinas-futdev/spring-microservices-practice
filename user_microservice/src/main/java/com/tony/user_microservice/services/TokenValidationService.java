package com.tony.user_microservice.services;

import com.tony.user_microservice.exceptions.InvalidTokenException;
import com.tony.user_microservice.model.Token;
import com.tony.user_microservice.utils.JwtTokenParser;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenValidationService {
        private final JwtTokenParser parser;

        public boolean isTokenExpired(String tokenValue) {
            Date today = new Date(System.currentTimeMillis());
            Date expiredDate = parser.parse(tokenValue).getExpiration();
            return today.after(expiredDate);
        }

        public void validateRefreshToken(Token token){
            Claims claims=parser.parse(token.getValue());
            if (isTokenExpired(token.getValue()) || token.isRevoked() || !claims.get("token_type").equals("refresh_token")){
                throw new InvalidTokenException("Token invalid or expired");
            }

        }

    }



