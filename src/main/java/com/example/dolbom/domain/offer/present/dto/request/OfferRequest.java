package com.example.dolbom.domain.offer.present.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class OfferRequest {

    private Boolean babySitter;

    @NotBlank(message = "title은 Null을 허용하지 않습니다.")
    @Size(max = 50, message = "title은 50자 이하여야 합니다.")
    private String title;

    @NotBlank(message = "content는 Null을 허용하지 않습니다.")
    @Size(max = 200, message = "content는 200자 이하여야 합니다.")
    private String content;

    @NotBlank(message = "intro Null을 허용하지 않습니다.")
    @Size(max = 200, message = "intro는 200자 이하여야 합니다.")
    private String intro;
}
