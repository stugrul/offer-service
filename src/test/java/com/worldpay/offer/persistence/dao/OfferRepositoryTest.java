package com.worldpay.offer.persistence.dao;

import com.worldpay.offer.persistence.model.Offer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class OfferRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OfferRepository offerRepository;

    @Test
    void shouldFindByName_thenReturnOffer() {
        Offer offer = getOffer();
        entityManager.persist(offer);
        entityManager.flush();

        Optional<Offer> found = offerRepository.findById(1L);

        if (found.isPresent()) {
            assertEquals(found.get().getId(), offer.getId());
        } else {
            fail("Should not reach here");
        }
    }

    private static Offer getOffer() {
        Offer offer = new Offer();

        offer.setName("Garfield");
        offer.setCurrency("EUR");
        offer.setDescription("This is a Garfield toy");
        offer.setId(1L);
        offer.setPrice(BigDecimal.valueOf(2));
        offer.setValidUntil(LocalDateTime.of(LocalDate.of(2018, Month.DECEMBER, 31), LocalTime.MIDNIGHT));

        return offer;
    }
}