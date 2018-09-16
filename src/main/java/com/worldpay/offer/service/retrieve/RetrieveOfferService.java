package com.worldpay.offer.service.retrieve;

import com.worldpay.offer.exception.OfferServiceException;
import com.worldpay.offer.persistence.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RetrieveOfferService {
    private JpaRepository<Offer, Long> offerJpaRepository;

    private RetrieveOfferService(final JpaRepository<Offer, Long> offerJpaRepository) {
        this.offerJpaRepository = offerJpaRepository;
    }

    public Offer findById(final Long id) {
        return offerJpaRepository.findById(id).orElseThrow(() -> new OfferServiceException("Offer does not exist"));
    }
}
