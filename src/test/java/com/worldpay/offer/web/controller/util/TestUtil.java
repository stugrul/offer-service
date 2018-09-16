package com.worldpay.offer.web.controller.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.worldpay.offer.persistence.model.Offer;

import java.math.BigDecimal;

public class TestUtil {
    public static Offer getOffer() {
        Offer offer = new Offer();

        offer.setName("Garfield");
        offer.setCurrency("EUR");
        offer.setDescription("This is a Garfield toy");
        offer.setId(1L);
        offer.setPrice(BigDecimal.valueOf(2));
        offer.setValidUntil("2018-10-10");

        return offer;
    }

    public static String asJsonString(final Object offer) {
        try {
            return new ObjectMapper().writeValueAsString(offer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
