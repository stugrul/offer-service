package com.worldpay.offer.service.retrieve;

import com.worldpay.offer.exception.OfferServiceException;
import com.worldpay.offer.persistence.model.Offer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveOfferServiceTest {

    @Mock
    private Offer offer;

    @Mock
    private JpaRepository<Offer, Long> offerJpaRepository;

    @InjectMocks
    private RetrieveOfferService retrieveOfferService;

    @Test
    void shouldRetrieveOfferById() {
        when(offerJpaRepository.findById(1L)).thenReturn(Optional.of(offer));

        Offer found = retrieveOfferService.findById(1L);

        assertAll(
                () -> verify(offerJpaRepository).findById(1L),
                () -> assertSame(offer, found)
        );
    }

    @Test
    void shouldThrowException_WhenOfferDoesNotExist() {
        when(offerJpaRepository.findById(1L)).thenReturn(Optional.empty());

        OfferServiceException offerServiceException = assertThrows(OfferServiceException.class, () -> retrieveOfferService.findById(1L));

        assertEquals("Offer does not exist", offerServiceException.getMessage());
    }
}