package com.worldpay.offer.web;

import com.worldpay.offer.exceptionhandler.exception.OfferServiceBadRequest;
import org.junit.jupiter.api.Test;

import static com.worldpay.offer.web.RestPreconditions.NULL_REQUEST_ELEMENT_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RestPreconditionsTest {

    private static final String ERROR_MESSAGE = "Error Message";

    @Test
    void should_ThrowOfferServiceBadRequestException_WhenIdsDoNotMatch() {
        OfferServiceBadRequest offerServiceBadRequest = assertThrows(OfferServiceBadRequest.class, () -> RestPreconditions.checkIfBadRequest(false, ERROR_MESSAGE));

        assertEquals(ERROR_MESSAGE, offerServiceBadRequest.getMessage());
    }

    @Test
    void should_ThrowOfferServiceBadRequestException_WhenRequestElementIsNull() {
        OfferServiceBadRequest offerServiceBadRequest = assertThrows(OfferServiceBadRequest.class, () -> RestPreconditions.checkRequestElementNotNull(null));
        assertEquals(NULL_REQUEST_ELEMENT_MESSAGE, offerServiceBadRequest.getMessage());
    }

    @Test
    void should_NotThrowAnError() {
        Object o = new Object();
        assertSame(o, RestPreconditions.checkRequestElementNotNull(o));
    }
}