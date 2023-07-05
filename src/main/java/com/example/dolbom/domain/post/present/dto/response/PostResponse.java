package com.example.dolbom.domain.post.present.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String category;
    private String title;
    private String content;
    private String region;
    private Long view;
}