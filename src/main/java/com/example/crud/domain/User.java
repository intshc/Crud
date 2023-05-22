package com.example.crud.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "user")
@Getter
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String username;
    private String password;
    private String email;
    private Integer age;
    private LocalDateTime created_at;

    @Builder
    public User(String username, String password, String email, Integer age) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.created_at = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Session> sessions = new ArrayList<>();

    public Session addSession(){
        Session session = Session
                .builder()
                .user(this)
                .build();
        sessions.add(session);

        return session;
    }
}
