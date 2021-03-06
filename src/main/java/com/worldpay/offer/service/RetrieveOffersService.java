package com.worldpay.offer.service;

import com.worldpay.offer.exceptionhandler.exception.OfferServiceResourceNotFoundException;
import com.worldpay.offer.persistence.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        Offer offer = offerJpaRepository.findById(id).orElseThrow(() -> new OfferServiceResourceNotFoundException("Offer does not exist"));

        if (isOfferExpired(offer)) {
            offerJpaRepository.delete(offer);
            throw new OfferServiceResourceNotFoundException("Offer has expired");
        }

        return offer;
    }

    private boolean isOfferExpired(final Offer offer) {
        String validUntil = offer.getValidUntil();

        LocalDate validUntilLocalDate = LocalDate.parse(validUntil);

        return LocalDateTime.of(validUntilLocalDate, LocalTime.MIDNIGHT).isBefore(LocalDateTime.now());
    }
}
