package com.example.dolbom.domain.offer.service;

import com.example.dolbom.domain.offer.domain.Offer;
import com.example.dolbom.domain.offer.domain.repository.OfferRepository;
import com.example.dolbom.domain.offer.exception.OfferAccessDeniedException;
import com.example.dolbom.domain.offer.exception.OfferNotFoundException;
import com.example.dolbom.domain.offer.present.dto.request.OfferRequest;
import com.example.dolbom.domain.offer.present.dto.response.OfferDetailResponse;
import com.example.dolbom.domain.offer.present.dto.response.OfferListResponse;
import com.example.dolbom.domain.offer.present.dto.response.OfferResponse;
import com.example.dolbom.domain.user.domain.User;
import com.example.dolbom.domain.user.domain.repository.UserRepository;
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
    private final UserRepository userRepository;

    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public OfferListResponse findAllDesc(){
        List<OfferResponse> offerList = offerRepository.findAllByOrderByIdDesc().stream()
                .map(offer -> new OfferResponse(
                        offer.getId(),
                        offer.getBabySitter(),
                        offer.getTitle(),
                        offer.getContent().substring(0, Math.min(offer.getContent().length(), 50)),
                        offer.getRegion(),
                        offer.getView()
                )).collect(Collectors.toList());

        return new OfferListResponse(offerList);
    }

    @Transactional(readOnly = true)
    public OfferDetailResponse findOfferById(Long id){
        Offer offer = offerRepository.findOfferById(id)
                .orElseThrow(()-> OfferNotFoundException.EXCEPTION);
        return OfferDetailResponse.builder()
                .id(offer.getId())
                .babySitter(offer.getBabySitter())
                .title(offer.getTitle())
                .content(offer.getContent())
                .intro(offer.getIntro())
                .author(offer.getAuthor())
                .certification(offer.getCertification())
                .view(offer.getView())
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


    @Transactional
    public void updateView(Long id){
        offerRepository.updateView(id);
    }
}