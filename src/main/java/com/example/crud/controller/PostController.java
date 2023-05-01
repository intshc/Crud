package com.example.crud.controller;

import com.example.crud.request.PostModify;
import com.example.crud.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping("/post/{postId}")
    public String show(){
        return "ㅎㅇ";
    }

    @DeleteMapping("/post/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.postDelete(postId);
    }

    @PatchMapping("/post/{postId}")
    public void updatePost(@PathVariable Long postId, PostModify request) {
        postService.postModify(postId, request);
    }
}