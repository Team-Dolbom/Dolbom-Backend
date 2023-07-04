package com.example.dolbom.domain.offer.domain.repository;

import com.example.dolbom.domain.offer.domain.Offer;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface OfferRepository extends CrudRepository<Offer, Long> {

    List<Offer> findAllByOrderByIdDesc();

    Optional<Offer> findById(Long id);
    Optional<Offer> findOfferById(Long id);


}
