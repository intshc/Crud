package com.example.crud.controller;

import com.example.crud.domain.Posts;
import com.example.crud.repository.PostRepository;
import com.example.crud.request.PostCreateDto;
import com.example.crud.request.PostEdit;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@AutoConfigureMockMvc
@SpringBootTest
class PostControllerTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void Clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성하기")
    public void CreatePost() throws Exception {
        //given
        Posts posts = Posts.builder()
                .title("글 제목")
                .content("글 내용")
                .build();

        String json = objectMapper.writeValueAsString(posts);


        //expected
        mockMvc.perform(MockMvcRequestBuilders.post("/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        //mockMvc를 실행하면 repository에 값이 들어 간다. 이전에 get 요청하면 out of index 나오고 repo에 save하면 값이 두개가 된다.
        Posts posts1 = postRepository.findAll().get(0);

        Assertions.assertEquals(1L, postRepository.count());

        Assertions.assertEquals(posts1.getTitle(), "글 제목");
        Assertions.assertEquals(posts1.getContent(), "글 내용");
    }

    @Test
    @DisplayName("글 읽기")
    public void ReadPost() throws Exception {
        //given
        Posts post = Posts.builder()
                .title("글 제목")
                .content("글 내용")
                .build();
        postRepository.save(post);

        //expected
        mockMvc.perform(MockMvcRequestBuilders.get("/post/{postId}", post.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("글 제목"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("글 내용"))
                .andDo(MockMvcResultHandlers.print());

        Assertions.assertEquals(postRepository.count(), 1);
    }

    @Test
    @DisplayName("제목, 내용 바꾸기")
    public void ChangePost() throws Exception {
        //given
        Posts post = Posts.builder()
                .title("글 제목")
                .content("글 내용")
                .build();

        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("수정된 제목")
                .content("수정된 내용")
                .build();

        //expect
        mockMvc.perform(MockMvcRequestBuilders.patch("/post/{postId}",post.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postEdit)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        Posts posts1 = postRepository.findAll().get(0);

        Assertions.assertEquals(posts1.getTitle(), "수정된 제목");
        Assertions.assertEquals(posts1.getContent(), "수정된 내용");
    }

    @Test
    @DisplayName("글 삭제하기")
    public void DeletePost() throws Exception {
        //given
        Posts posts = Posts.builder()
                .title("글 제목")
                .content("글 내용")
                .build();
        //when
        postRepository.save(posts);

        //then
        Assertions.assertEquals(1L, postRepository.count());

        //expected
        mockMvc.perform(MockMvcRequestBuilders.delete("/post/{postId}", posts.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        Assertions.assertEquals(0, postRepository.count());
    }
}