package com.example.crud.service;

import com.example.crud.domain.Posts;
import com.example.crud.exception.PostNotFound;
import com.example.crud.repository.PostRepository;
import com.example.crud.request.PostCreateDto;
import com.example.crud.request.PostEdit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clear() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    void createPost() throws Exception {
        //given
        postService.postCreate(PostCreateDto.builder()
                .title("글 제목")
                .content("글 내용")
                .build());

        //when
        Posts posts = postRepository.findAll().get(0);

        //then
        Assertions.assertEquals(posts.getTitle(), "글 제목");
        Assertions.assertEquals(posts.getContent(), "글 내용");
    }

    @Test
    @DisplayName("글 2개 조회하기")
    void readPost() throws Exception {
        //given
        postService.postCreate(PostCreateDto.builder()
                .title("첫번째 글 제목")
                .content("첫번째 글 내용")
                .build());

        postService.postCreate(PostCreateDto.builder()
                .title("두번째 글 제목")
                .content("두번째 글 내용")
                .build());

        //when
        Posts firstPost = postRepository.findAll().get(0);
        Posts secPost = postRepository.findAll().get(1);

        //then
        Assertions.assertEquals(firstPost.getTitle(), "첫번째 글 제목");
        Assertions.assertEquals(secPost.getTitle(), "두번째 글 제목");

        Assertions.assertEquals(firstPost.getContent(), "첫번째 글 내용");
        Assertions.assertEquals(secPost.getContent(), "두번째 글 내용");
    }

    @Test
    @DisplayName("글 수정하기")
    void updatePost() throws Exception {
        //given
        Posts post = Posts.builder()
                .title("수정 전 제목")
                .content("수정 전 내용")
                .build();

        postRepository.save(post);
        //when

        PostEdit postEdit = PostEdit
                .builder()
                .title("수정 후 제목")
                .content("수정 후 내용")
                .build();
        postService.postEdit(post.getId(), postEdit);

        //then
        Posts editedPost = postRepository.findAll().get(0);

        Assertions.assertEquals(editedPost.getTitle(), "수정 후 제목");
        Assertions.assertEquals(editedPost.getContent(), "수정 후 내용");

    }

    @Test
    @DisplayName("글 삭제하기")
    void deletePost() throws Exception {
        //given
        Posts post = Posts.builder()
                .title("글 제목")
                .content("글 내용")
                .build();

        //when
        postRepository.save(post);

        postService.postDelete(post.getId());

        //then
        Assertions.assertEquals(postRepository.count(), 0);
    }

    @Test
    @DisplayName("없는 글 조회")
    void findNonexistentPost() throws Exception {
        //expect
        Assertions.assertThrows(PostNotFound.class, () -> postService.postShow(0L));

    }

    @Test
    @DisplayName("없는 글 수정")
    void updatingNonexistentPost() throws Exception {
        //expect
        Assertions.assertThrows(PostNotFound.class, () -> postService.postEdit(0L, PostEdit
                .builder()
                .title("없는 글 수정")
                .content("없는 내용 수정")
                .build()));
    }

}