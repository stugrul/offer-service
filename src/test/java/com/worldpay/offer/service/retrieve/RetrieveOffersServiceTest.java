package com.worldpay.offer.service.retrieve;

import com.worldpay.offer.persistence.model.Offer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
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
}