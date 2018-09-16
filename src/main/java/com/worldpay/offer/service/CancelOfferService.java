package com.worldpay.offer.service;

import com.worldpay.offer.persistence.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CancelOfferService {

    private JpaRepository<Offer, Long> offerJpaRepository;
    private RetrieveOffersService retrieveOffersService;

    private CancelOfferService(final JpaRepository<Offer, Long> offerJpaRepository, final RetrieveOffersService retrieveOffersService) {
        this.offerJpaRepository = offerJpaRepository;
        this.retrieveOffersService = retrieveOffersService;
    }

    public void cancel(final long id) {
        Offer offer = retrieveOffersService.findById(id);
        offerJpaRepository.delete(offer);
    }
}
