package com.example.dolbom.domain.offer.exception;

import com.example.dolbom.global.error.exception.ErrorCode;
import com.example.dolbom.global.error.exception.ProjectException;

public class OfferNotFoundException extends ProjectException {
    public static final OfferNotFoundException EXCEPTION =
            new OfferNotFoundException();

    public OfferNotFoundException() {
        super(ErrorCode.OFFER_NOT_FOUND);
    }
}
