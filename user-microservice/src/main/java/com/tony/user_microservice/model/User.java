package com.tony.user_microservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.Set;


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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_user",joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name ="role_id"))
    private Set<Roles> roles;


}
