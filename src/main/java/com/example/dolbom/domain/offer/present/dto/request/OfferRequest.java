package com.example.dolbom.domain.offer.present.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OfferRequest {
    private Boolean babySitter;
    private String title;
    private String content;
    private String intro;
}
