package com.worldpay.offer.service;

import com.worldpay.offer.persistence.model.Offer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CancelOfferServiceTest {
    @InjectMocks
    private CancelOfferService cancelOfferService;

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

        doNothing().when(offerJpaRepository).delete(offer);

        cancelOfferService.cancel(1L);

        assertAll(
                () -> verify(offerJpaRepository).delete(offer)
        );
    }

    @Test
    void should_NotProcess_WhenOfferDoesNotExist() {
        when(offer.getId()).thenReturn(1L);

        when(retrieveOffersService.findById(offer.getId())).thenReturn(null);

        cancelOfferService.cancel(1L);

        assertAll(
                () -> verify(offerJpaRepository, never()).delete(offer)
        );
    }
}