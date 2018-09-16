package com.worldpay.offer.web;

import com.worldpay.offer.exceptionhandler.exception.OfferServiceBadRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RestPreConditionsTest {

    private static final String ERROR_MESSAGE = "Error Message";

    @Test
    void should_ThrowOfferBadRequestException() {
        OfferServiceBadRequest offerServiceBadRequest = assertThrows(OfferServiceBadRequest.class, () -> RestPreConditions.checkIfBadRequest(false, ERROR_MESSAGE));

        assertEquals(ERROR_MESSAGE, offerServiceBadRequest.getMessage());
    }
}