package com.example.crud.controller;

import com.example.crud.domain.Posts;
import com.example.crud.request.PostCreateDto;
import com.example.crud.request.PostEdit;
import com.example.crud.response.PostView;
import com.example.crud.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    //게시글 생성
    @PostMapping("/posts")
    public void setPost(@RequestBody @Valid PostCreateDto request) {
        postService.postCreate(request);
    }

    //게시글 단건 조회
    @GetMapping("/post/{postId}")
    public PostView getPost(@PathVariable Long postId) {
        return postService.postShow(postId);
    }

    //게시글 전체 조회
    @GetMapping("/posts")
    public List<PostView> postAllViews() {
        return postService.postAllViews();
    }

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

    //postss로 잘못 입력 시 /posts로 리다이렉트
    @RequestMapping("/postss")
    public void redirect(HttpServletResponse response) throws Exception {
        String redirect_uri = "http://localhost:8080/posts";
        response.sendRedirect(redirect_uri);
    }

}