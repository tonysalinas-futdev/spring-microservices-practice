package com.tony.user_microservice.services;

import com.tony.user_microservice.exceptions.NotFoundException;
import com.tony.user_microservice.model.Token;
import com.tony.user_microservice.model.User;
import com.tony.user_microservice.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository repo;

    public Token create(User user, String value){
        Token token=Token.builder().user(user).value(value).revoked(false).expired(false).build();
        repo.saveAndFlush(token);
        return token;
    }

    public Token getToken(String value) {
        return repo.findByValue(value).orElseThrow(() -> new NotFoundException("Token not found"));
    }

    public void revokeAllValidTokensForUser(Long userId){
        List<Token> tokens=repo.findByUser_Id(userId);
        for (Token token: tokens ){
            token.setRevoked(true);
        }
        repo.saveAll(tokens);
    }
}
