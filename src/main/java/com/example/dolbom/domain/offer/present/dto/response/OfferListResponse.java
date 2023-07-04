package com.example.dolbom.domain.offer.present.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OfferListResponse {
    List<OfferResponse> offerList;
}
