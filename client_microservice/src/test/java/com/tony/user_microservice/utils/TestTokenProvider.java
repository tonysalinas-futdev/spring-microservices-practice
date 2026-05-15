package com.tony.user_microservice.utils;

import com.tony.user_microservice.enums.Role;
import com.tony.user_microservice.model.Roles;
import com.tony.user_microservice.model.User;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestTokenProvider {
    @Autowired
    private JwtTokenProvider provider;
    @Autowired
    private JwtTokenParser parser;

    User getUserForTest(){
        Roles rol= new Roles(5L, Role.CLIENT,null);
        return User.builder().id(4L).fullName("Juan Antonio Chao Salinas").email("kroosismo0202@gmail.com").build();

    }

    @Test
    void shouldCreateAccessTokenSuccessfully(){
        User user=getUserForTest();
        String token=provider.createAccessToken(user);
        Claims claims=parser.parse(token);

        Assertions.assertEquals("kroosismo0202@gmail.com",claims.getSubject());
        Assertions.assertEquals("4",claims.get("id"));
        Assertions.assertEquals("access_token", claims.get("token_type"));

    }

}
