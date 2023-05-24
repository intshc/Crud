package com.example.crud.service;

import com.example.crud.domain.Posts;
import com.example.crud.exception.PostNotFound;
import com.example.crud.repository.PostRepository;
import com.example.crud.request.PostCreateDto;
import com.example.crud.request.PostEdit;
import com.example.crud.response.PostView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 게시글 조회
    public PostView postShow(Long id) {
        Posts posts = postRepository.findById(id)
                .orElseThrow(()-> new PostNotFound("존재하지 않는 글 입니다."));

        return PostView.builder()
                .id(posts.getId())
                .title(posts.getTitle())
                .content(posts.getContent())
                .build();
    }

    // 게시글 생성
    public void postCreate(PostCreateDto request) {

        postRepository.save(Posts.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build());
    }

    // 게시글 삭제
    @Transactional
    public void postDelete(Long id) {
        postRepository.deleteById(id);
    }

    //게시글 수정
    @Transactional
    public void postEdit(Long id, PostEdit request) {

        Posts findPost = postRepository.findById(id)
                .orElseThrow(()-> new PostNotFound("존재하지 않는 글 입니다."));

        findPost.updatePost(request.getTitle(),request.getContent());
    }
}