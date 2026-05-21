package com.tony.user_microservice.repository;

import com.tony.user_microservice.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    Optional<Token> findByValue(String value);
    List<Token> findByUser_Id(Long userId);
}
