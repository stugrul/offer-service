package com.worldpay.offer.service;

import com.worldpay.offer.persistence.model.Offer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateOfferServiceTest {

    @InjectMocks
    private UpdateOfferService updateOfferService;

    @Mock
    private JpaRepository<Offer, Long> offerJpaRepository;

    @Mock
    private RetrieveOffersService retrieveOffersService;

    @Mock
    private Offer offer;

    @Test
    void should_UpdateOffer() {
        when(offer.getId()).thenReturn(1L);

        when(retrieveOffersService.findById(offer.getId())).thenReturn(offer);

        when(offerJpaRepository.save(offer)).thenReturn(offer);

        updateOfferService.update(1L, offer);

        assertAll(
                () -> verify(offerJpaRepository).save(offer)
        );
    }
}