package com.example.crud.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String accessToken;

    @ManyToOne
    private User user;

    @Builder
    public Session(String accessToken, User user) {
        this.accessToken = UUID.randomUUID().toString();
        this.user = user;
    }
}
