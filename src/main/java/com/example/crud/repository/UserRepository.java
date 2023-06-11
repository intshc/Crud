package com.example.crud.repository;

import com.example.crud.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long> {
    //Optional로 예외처리 가능하다
    Optional<User> findByEmailAndPassword(String email, String password);
}
