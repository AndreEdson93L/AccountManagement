package com.accountmanagement.Exercise.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String nickname;

    private Boolean consent;

    @Enumerated(EnumType.STRING)
    private Role role;
}
