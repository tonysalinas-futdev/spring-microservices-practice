package com.tony.user_microservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;


@Table(name = "users")
@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(unique = true)
    private String email;

    private int age;

    private String password;
    @CreationTimestamp
    private LocalDateTime registerAt;

    @Column(name = "is_enabled")
    @Builder.Default
    private Boolean isEnabled=true;

    @Column(name = "account_no_locked")
    @Builder.Default
    private Boolean accountNoLocked=true;

    @Column(name = "credentials_no_expired")
    @Builder.Default
    private Boolean credentialsNoExpired=true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Roles rol;


}
