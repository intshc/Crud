package com.example.crud.controller;

import com.example.crud.request.PostCreateDto;
import com.example.crud.request.PostEdit;
import com.example.crud.response.PostView;
import com.example.crud.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    //게시글 생성
    @PostMapping("/post")
    public void setPost(@RequestBody @Valid PostCreateDto request){
        postService.postCreate(request);
    }

    //게시글 단건 조회
    @GetMapping("/post/{postId}")
    public PostView getPost(@PathVariable Long postId){
        return postService.postShow(postId);
    }

    //게시글 전체 조회
    //@GetMapping("/post")

    //게시글 삭제
    @DeleteMapping("/post/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.postDelete(postId);
    }

    //게시글 수정
    @PatchMapping("/post/{postId}")
    public void updatePost(@PathVariable Long postId, @RequestBody PostEdit request) {
        postService.postEdit(postId, request);
    }
}