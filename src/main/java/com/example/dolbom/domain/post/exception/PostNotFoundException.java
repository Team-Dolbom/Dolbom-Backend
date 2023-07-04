package com.example.dolbom.domain.post.exception;

import com.example.dolbom.global.error.exception.ErrorCode;
import com.example.dolbom.global.error.exception.ProjectException;

public class PostNotFoundException extends ProjectException {
    public static final PostNotFoundException EXCEPTION =
            new PostNotFoundException();

    private PostNotFoundException(){
        super(ErrorCode.POST_NOT_FOUND);
    }
}