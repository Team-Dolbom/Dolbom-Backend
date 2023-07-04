package com.example.dolbom.domain.auth.present.dto.response;

import com.example.dolbom.global.enums.Authority;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SmsCodeResponse {
    private String code;
}