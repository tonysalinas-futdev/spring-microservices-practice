package com.tony.user_microservice.repository;

import com.tony.user_microservice.enums.Role;
import com.tony.user_microservice.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long>{
    Optional<Roles> findByName(Role name);
}
