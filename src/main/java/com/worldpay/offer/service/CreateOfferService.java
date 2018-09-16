package com.worldpay.offer.service;

import com.worldpay.offer.persistence.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateOfferService {

    private JpaRepository<Offer, Long> offerJpaRepository;

    private CreateOfferService(final JpaRepository<Offer, Long> offerJpaRepository) {
        this.offerJpaRepository = offerJpaRepository;
    }

    public void create(final Offer offer) {
        offerJpaRepository.save(offer);
    }
}
