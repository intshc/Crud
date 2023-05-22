package com.example.crud.controller;

import com.example.crud.config.data.UserSession;
import com.example.crud.request.Login;
import com.example.crud.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/auth")
    public Long authCheck(UserSession userSession){
        return userSession.id;
    }

}
