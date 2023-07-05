package com.example.dolbom.domain.post.present.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
public class PostRequest {

    @NotBlank(message = "category는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String category;

    @NotBlank(message = "title은 Null을 허용하지 않습니다.")
    @Size(max = 50, message = "title은 50자 이하여야 합니다.")
    private String title;

    @NotBlank(message = "content은 Null을 허용하지 않습니다.")
    @Size(max = 500, message = "content는 500자 이하여야 합니다.")
    private String content;

}
