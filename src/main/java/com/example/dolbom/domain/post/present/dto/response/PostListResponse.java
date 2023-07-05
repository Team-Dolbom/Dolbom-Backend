package com.example.dolbom.domain.post.present.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostListResponse {
     List<PostResponse> postList;

}
