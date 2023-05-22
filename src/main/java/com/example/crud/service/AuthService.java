package com.example.crud.service;

import com.example.crud.domain.Session;
import com.example.crud.domain.User;
import com.example.crud.repository.UserRepository;
import com.example.crud.request.Login;
import com.example.crud.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public Long sign(Login login){
        User user = userRepository.findByEmailAndPassword(login.getEmail(),login.getPassword())
                .orElseThrow(NoSuchElementException::new);
        Session session = user.addSession();
        return user.getId();
    }
}
