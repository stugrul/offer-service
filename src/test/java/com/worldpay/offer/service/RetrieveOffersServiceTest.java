package com.worldpay.offer.service;

import com.worldpay.offer.exceptionhandler.exception.OfferServiceResourceNotFoundException;
import com.worldpay.offer.persistence.model.Offer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveOffersServiceTest {
    @Mock
    private Offer offer;

    @Mock
    private JpaRepository<Offer, Long> offerJpaRepository;

    @InjectMocks
    private RetrieveOffersService retrieveOffersService;

    @Test
    void should_FindAllOffers() {
        when(offerJpaRepository.findAll()).thenReturn(Collections.singletonList(offer));

        List<Offer> offers = retrieveOffersService.findAll();

        assertAll(
                () -> verify(offerJpaRepository).findAll(),
                () -> assertSame(offer, offers.get(0)),
                () -> assertEquals(1, offers.size())
        );
    }

    @Test
    void should_FindSpecificOffer() {
        when(offerJpaRepository.findById(1L)).thenReturn(Optional.of(offer));
        when(offer.getValidUntil()).thenReturn("2019-08-16");

        Offer found = retrieveOffersService.findById(1L);

        assertAll(
                () -> verify(offerJpaRepository).findById(1L),
                () -> assertSame(found, offer)
        );
    }

    @Test
    void should_ThrowException_WhenOfferIsExpired() {
        when(offerJpaRepository.findById(1L)).thenReturn(Optional.of(offer));
        when(offer.getValidUntil()).thenReturn("2016-08-16");

        OfferServiceResourceNotFoundException offerServiceResourceNotFoundException = assertThrows(OfferServiceResourceNotFoundException.class,
                                                                                                   () -> retrieveOffersService.findById(1L));

        assertEquals("Offer has expired", offerServiceResourceNotFoundException.getMessage());
    }

    @Test
    void should_ThrowException_WhenOfferDoesNotExist() {
        when(offerJpaRepository.findById(1L)).thenReturn(Optional.empty());

        OfferServiceResourceNotFoundException offerServiceResourceNotFoundException = assertThrows(OfferServiceResourceNotFoundException.class,
                                                                                                   () -> retrieveOffersService.findById(1L));

        assertEquals("Offer does not exist", offerServiceResourceNotFoundException.getMessage());
    }
}