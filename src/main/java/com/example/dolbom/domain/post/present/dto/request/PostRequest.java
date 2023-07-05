package com.example.dolbom.domain.post.present.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
public class PostRequest {

    @NotBlank
    private String category;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
