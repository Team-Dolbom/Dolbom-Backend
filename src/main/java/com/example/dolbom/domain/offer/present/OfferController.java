package com.example.dolbom.domain.offer.present;

import com.example.dolbom.domain.offer.present.dto.request.OfferRequest;
import com.example.dolbom.domain.offer.present.dto.response.OfferListResponse;
import com.example.dolbom.domain.offer.present.dto.response.OfferResponse;
import com.example.dolbom.domain.offer.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


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
    public OfferResponse getOfferById(@PathVariable Long id){
        return offerService.findOfferById(id);
    }

    @PostMapping("/create")
    public void createOffer(@RequestBody OfferRequest request){
        offerService.save(request);
    }

    @PutMapping("/{id}")
    public void updateOffer(@RequestBody OfferRequest request, @PathVariable Long id){
        offerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteOffer(@PathVariable Long id){
        offerService.deleteOfferById(id);
    }
}
