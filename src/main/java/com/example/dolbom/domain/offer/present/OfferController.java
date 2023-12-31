package com.example.dolbom.domain.offer.present;

import com.example.dolbom.domain.offer.present.dto.request.OfferRequest;
import com.example.dolbom.domain.offer.present.dto.response.OfferDetailResponse;
import com.example.dolbom.domain.offer.present.dto.response.OfferListResponse;
import com.example.dolbom.domain.offer.present.dto.response.OfferResponse;
import com.example.dolbom.domain.offer.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/offer")
@RequiredArgsConstructor
public class OfferController {
    private final OfferService offerService;

    @GetMapping("/")
    public OfferListResponse getOffers(){
        return offerService.findAllDesc();
    }

    @GetMapping("/{id}")
    public OfferDetailResponse getOfferById(@PathVariable Long id){
        offerService.updateView(id);
        return offerService.findOfferById(id);
    }

    @PostMapping("/create")
    public void createOffer(@RequestBody @Valid OfferRequest request){
        offerService.save(request);
    }

    @PutMapping("/{id}")
    public void updateOffer(@RequestBody @Valid OfferRequest request, @PathVariable Long id){
        offerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteOffer(@PathVariable Long id){
        offerService.deleteOfferById(id);
    }
}
