package com.worldpay.offer.service.retrieve;

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
}
