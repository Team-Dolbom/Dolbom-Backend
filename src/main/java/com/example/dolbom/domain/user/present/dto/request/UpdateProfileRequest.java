package com.example.dolbom.domain.user.present.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UpdateProfileRequest {

    @NotBlank(message = "nickname은 Null을 허용하지 않습니다.")
    private String nickname;

    private Boolean certification;
}
