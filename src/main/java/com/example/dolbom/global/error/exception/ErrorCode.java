package com.example.dolbom.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    USER_NOT_FOUND(404, "USER-404-1", "User Not Found"),
    USER_ALREADY_JOINED(409, "USER-409-1", "User Already Joined"),
    USER_ACCESS_DENIED(403, "USER-403-1", "User Access Denied"),

    INVALID_JWT(401, "JWT-401-1", "Invalid Jwt"),
    EXPIRED_JWT(401, "JWT-401-2", "Expired JWT"),
    REFRESH_NOT_FOUND(404, "JWT-404-1", "RefreshToken Not Found"),

    PASSWORD_NOT_MATCH(401, "AUTH-401-1", "Password Not Match"),

    POST_ACCESS_DENIED(403, "POST-403-1", "Access Denied"),
    POST_NOT_FOUND(404, "POST-404-1", "Post Not Found"),

    OFFER_ACCESS_DENIED(403, "OFFER-403-1", "Access Denied"),
    OFFER_NOT_FOUND(404, "OFFER-404-1", "Offer Not Found");

    private final int status;
    private final String code;
    private final String message;
}
