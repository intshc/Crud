package com.example.crud.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PostEdit {

    @NotNull(message = "제목을 입력해 주세요")
    private String title;
    @NotNull(message = "내용을 입력해 주세요")
    private String content;

    @Builder
    public PostEdit(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
