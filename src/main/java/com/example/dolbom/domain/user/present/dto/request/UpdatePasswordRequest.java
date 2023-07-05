package com.example.dolbom.domain.user.present.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UpdatePasswordRequest {

    @NotBlank(message = "password는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 6, max = 20, message = "password는 6자 이상, 20자 이하여야 합니다.")
    private String password;

    @NotBlank(message = "newPassword는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 6, message = "newPassword는 6자 이상이여야 합니다")
    private String newPassword;
}
