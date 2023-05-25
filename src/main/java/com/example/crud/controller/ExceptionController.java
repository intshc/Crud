package com.example.crud.controller;

import com.example.crud.exception.PostNotFound;
import com.example.crud.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ExceptionController extends RuntimeException {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = PostNotFound.class)
    public ErrorResponse exceptionHandler(PostNotFound e) {

        return ErrorResponse
                .builder()
                .errorMessage(e.getMessage())
                .errorCode(404)
                .build();
    }
}
