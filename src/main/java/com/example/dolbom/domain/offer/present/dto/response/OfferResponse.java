package com.example.dolbom.domain.offer.present.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OfferResponse {
    private Long id;
    private String title;
    private String content;
    private String author;
    private Boolean certification;
}
