package com.example.crud.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostView {

    private final int id;
    private final String title;
    private final String content;

    @Builder
    public PostView(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
