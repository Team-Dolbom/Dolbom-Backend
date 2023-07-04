package com.example.dolbom.domain.offer.exception;

import com.example.dolbom.global.error.exception.ErrorCode;
import com.example.dolbom.global.error.exception.ProjectException;

public class OfferAccessDeniedException extends ProjectException {
    public final static OfferNotFoundException EXCEPTION =
            new OfferNotFoundException();

    private OfferAccessDeniedException(){
        super(ErrorCode.OFFER_ACCESS_DENIED);
    }
}
