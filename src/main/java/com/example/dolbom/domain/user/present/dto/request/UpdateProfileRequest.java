package com.example.dolbom.domain.user.present.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateProfileRequest {
    private String nickname;
    private Boolean certification;
}
