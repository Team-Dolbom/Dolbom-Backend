package com.example.dolbom.domain.user.exception;

import com.example.dolbom.global.error.exception.ErrorCode;
import com.example.dolbom.global.error.exception.ProjectException;

public class UserAccessDeniedException extends ProjectException {
    public final static UserAccessDeniedException EXCEPTION =
            new UserAccessDeniedException();

    private UserAccessDeniedException(){
        super(ErrorCode.USER_ACCESS_DENIED);
    }
}
