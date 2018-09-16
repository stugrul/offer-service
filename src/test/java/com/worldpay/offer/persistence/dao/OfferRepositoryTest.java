package com.worldpay.offer.persistence.dao;

import com.worldpay.offer.persistence.model.Offer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.worldpay.offer.web.controller.util.TestUtil.getOffer;
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
}