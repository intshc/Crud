package com.example.crud.repository;

import com.example.crud.domain.Posts;
import com.example.crud.response.PostView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT new com.example.crud.response.PostView(p.id, p.title, p.content)" +
            "from Posts p ORDER BY p.id DESC")
    List<PostView> findAllDesc();
}