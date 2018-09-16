package com.worldpay.offer.service;

import com.worldpay.offer.exception.OfferServiceResourceNotFoundException;
import com.worldpay.offer.persistence.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetrieveOffersService {

    private JpaRepository<Offer, Long> offerJpaRepository;

    private RetrieveOffersService(final JpaRepository<Offer, Long> offerJpaRepository) {
        this.offerJpaRepository = offerJpaRepository;
    }

    public List<Offer> findAll() {
        return offerJpaRepository.findAll();
    }

    public Offer findById(final Long id) {
        return offerJpaRepository.findById(id).orElseThrow(() -> new OfferServiceResourceNotFoundException("Offer does not exist"));
    }
}
