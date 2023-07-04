package com.example.dolbom.domain.user.present.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MyPageResponse {
    private Boolean certification;
    private String nickName;
}
