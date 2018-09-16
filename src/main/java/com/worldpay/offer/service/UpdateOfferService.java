package com.worldpay.offer.service;

import com.worldpay.offer.persistence.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateOfferService {

    private JpaRepository<Offer, Long> offerJpaRepository;
    private RetrieveOffersService retrieveOffersService;

    private UpdateOfferService(final JpaRepository<Offer, Long> offerJpaRepository, final RetrieveOffersService retrieveOffersService) {
        this.offerJpaRepository = offerJpaRepository;
        this.retrieveOffersService = retrieveOffersService;
    }

    public void update(final Long id, final Offer offer) {
        if (retrieveOffersService.findById(id) != null) {
            offerJpaRepository.save(offer);
        }
    }
}
