package com.worldpay.offer.web;

import com.worldpay.offer.exceptionhandler.exception.OfferServiceBadRequest;
import com.worldpay.offer.persistence.model.Offer;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public final class RestPreconditions {
    static final String NULL_REQUEST_ELEMENT_MESSAGE = "Null request element detected";
    static final String INVALID_DATE_MESSAGE = "invalid date, date should follow yyyy-MM-dd format";

    public static void checkIfIdsMatch(final boolean expression, final String message) {
        if (!expression) {
            throw new OfferServiceBadRequest(message);
        }
    }

    public static void checkIfBadRequest(final Offer offer) {
        checkRequestElementNotNull(offer);
        checkRequestElementNotNull(offer.getId());
        checkIfDateFieldIsValid(offer);
    }

    private static void checkIfDateFieldIsValid(final Offer offer) {
        try {
            LocalDate.parse(offer.getValidUntil());
        } catch (DateTimeParseException ex) {
            throw new OfferServiceBadRequest(INVALID_DATE_MESSAGE);
        }
    }

    private static <T> void checkRequestElementNotNull(final T requestElement) {
        if (requestElement == null) {
            throw new OfferServiceBadRequest(NULL_REQUEST_ELEMENT_MESSAGE);
        }
    }
}
