package com.example.dolbom.domain.offer.service;

import com.example.dolbom.domain.offer.domain.Offer;
import com.example.dolbom.domain.offer.domain.repository.OfferRepository;
import com.example.dolbom.domain.offer.exception.OfferAccessDeniedException;
import com.example.dolbom.domain.offer.exception.OfferNotFoundException;
import com.example.dolbom.domain.offer.present.dto.request.OfferRequest;
import com.example.dolbom.domain.offer.present.dto.response.OfferListResponse;
import com.example.dolbom.domain.offer.present.dto.response.OfferResponse;
import com.example.dolbom.domain.user.domain.User;
import com.example.dolbom.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;

    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public OfferListResponse findAllDesc(){
        User user = userFacade.getCurrentUser();
        List<OfferResponse> offerList = offerRepository.findAllByOrderByIdDesc().stream()
                .map(offer -> new OfferResponse(
                        offer.getId(),
                        offer.getTitle(),
                        offer.getContent().substring(0, Math.min(offer.getContent().length(), 50)),
                        offer.getIntro(),
                        user.getCertification()
                )).collect(Collectors.toList());

        return new OfferListResponse(offerList);
    }

    @Transactional(readOnly = true)
    public OfferResponse findOfferById(Long id){
        Offer offer = offerRepository.findOfferById(id)
                .orElseThrow(()-> OfferNotFoundException.EXCEPTION);

        Boolean certification = userFacade.getCurrentUser().getCertification();

        return OfferResponse.builder()
                .id(offer.getId())
                .title(offer.getTitle())
                .content(offer.getContent())
                .certification(certification)
                .author(offer.getAuthor())
                .build();
    }

    @Transactional
    public void save(OfferRequest request){
        offerRepository.save(
                Offer.builder()
                        .babySitter(request.getBabySitter())
                        .title(request.getTitle())
                        .content(request.getContent())
                        .intro(request.getIntro())
                        .author(userFacade.getCurrentUser().getAccountId())
                        .build()
        );
    }

    @Transactional
    public void update(Long id, OfferRequest request){
        Offer offer = offerRepository.findOfferById(id)
                .orElseThrow(()-> OfferNotFoundException.EXCEPTION);

        User currentUser = userFacade.getCurrentUser();

        if(!currentUser.getAccountId().equals(offer.getAuthor())){
            throw OfferAccessDeniedException.EXCEPTION;
        }

        offer.update(
                request.getBabySitter(),
                request.getTitle(),
                request.getContent(),
                request.getIntro()
        );
    }

    @Transactional
    public void deleteOfferById(Long id){
        Offer offer = offerRepository.findOfferById(id)
                .orElseThrow(()->OfferNotFoundException.EXCEPTION);

        User currentUser = userFacade.getCurrentUser();

        if(!currentUser.getAccountId().equals(offer.getAuthor())){
            throw OfferAccessDeniedException.EXCEPTION;
        }

        offerRepository.deleteById(id);
    }


}













