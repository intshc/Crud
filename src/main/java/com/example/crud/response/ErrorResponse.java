package com.example.crud.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private final String ErrorMessage;
    private final int ErrorCode;

    @Builder
    public ErrorResponse(String errorMessage, int errorCode) {
        ErrorMessage = errorMessage;
        ErrorCode = errorCode;
    }
}
