package com.worldpay.offer.web;

import com.worldpay.offer.exceptionhandler.exception.OfferServiceBadRequest;
import com.worldpay.offer.persistence.model.Offer;
import org.junit.jupiter.api.Test;

import static com.worldpay.offer.web.RestPreconditions.INVALID_DATE_MESSAGE;
import static com.worldpay.offer.web.RestPreconditions.NULL_REQUEST_ELEMENT_MESSAGE;
import static com.worldpay.offer.web.controller.util.TestUtil.getOffer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RestPreconditionsTest {
    private static final String ERROR_MESSAGE = "Error Message";

    @Test
    void should_ThrowOfferServiceBadRequestException_WhenIdsDoNotMatch() {
        OfferServiceBadRequest offerServiceBadRequest = assertThrows(OfferServiceBadRequest.class, () -> RestPreconditions.checkIfIdsMatch(false, ERROR_MESSAGE));

        assertEquals(ERROR_MESSAGE, offerServiceBadRequest.getMessage());
    }

    @Test
    void should_ThrowOfferServiceBadRequestException_WhenRequestElementIsNull() {
        OfferServiceBadRequest offerServiceBadRequest = assertThrows(OfferServiceBadRequest.class, () -> RestPreconditions.checkIfBadRequest(null));
        assertEquals(NULL_REQUEST_ELEMENT_MESSAGE, offerServiceBadRequest.getMessage());
    }

    @Test
    void should_ThrowOfferServiceBadRequestException_WhenDateIsInValid() {
        Offer offerInvalidDate = getOffer();
        offerInvalidDate.setValidUntil("2018-19-45");
        OfferServiceBadRequest offerServiceBadRequest = assertThrows(OfferServiceBadRequest.class, () -> RestPreconditions.checkIfBadRequest(offerInvalidDate));
        assertEquals(INVALID_DATE_MESSAGE, offerServiceBadRequest.getMessage());
    }
}