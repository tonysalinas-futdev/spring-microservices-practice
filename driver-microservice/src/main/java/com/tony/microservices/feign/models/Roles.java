package com.tony.microservices.feign.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.security.Permissions;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class Roles {

    private Long id;

    private Role name;

}
