package com.example.dolbom.domain.user.exception;

import com.example.dolbom.global.error.exception.ErrorCode;
import com.example.dolbom.global.error.exception.ProjectException;

public class AlreadyJoinedException extends ProjectException {
    public static final ProjectException EXCEPTION =
            new AlreadyJoinedException();

    private AlreadyJoinedException() {
        super(ErrorCode.USER_ALREADY_JOINED);
    }
}
