package com.example.dolbom.domain.post.exception;

import com.example.dolbom.global.error.exception.ErrorCode;
import com.example.dolbom.global.error.exception.ProjectException;

public class PostAccessDeniedException extends ProjectException {
    public final static PostAccessDeniedException EXCEPTION =
            new PostAccessDeniedException();

    private PostAccessDeniedException(){
        super(ErrorCode.POST_ACCESS_DENIED);
    }
}
