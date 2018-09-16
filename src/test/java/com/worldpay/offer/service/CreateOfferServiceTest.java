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
class CreateOfferServiceTest {

    @Mock
    private Offer offer;

    @Mock
    private JpaRepository<Offer, Long> offerJpaRepository;

    @InjectMocks
    private CreateOfferService createOfferService;

    @Test
    void should_CreateOffer() {

        when(offerJpaRepository.save(offer)).thenReturn(offer);

        createOfferService.create(offer);

        assertAll(
                () -> verify(offerJpaRepository).save(offer)
        );
    }
}