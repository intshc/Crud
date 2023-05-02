package com.example.crud.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class PostCreateDto {

    @NotNull(message = "제목을 입력해 주세요")
    private String title;
    @NotNull(message = "내용을 입력해 주세요")
    private String content;
}
