package com.example.crud.domain;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private int age;
}
