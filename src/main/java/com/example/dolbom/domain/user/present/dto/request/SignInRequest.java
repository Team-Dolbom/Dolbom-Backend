package com.example.dolbom.domain.user.present.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class SignInRequest {

    @NotBlank(message = "accountId는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 6, max = 20, message = "accountId는 6자 이상, 20자 이하여야 합니다.")
    private String accountId;

    @NotBlank(message = "password는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 6, message = "password는 영문, 숫자, 특수문자를 포함한 6자 이상이여야 합니다.")
    private String password;
}
