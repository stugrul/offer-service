package com.worldpay.offer.web;

import com.worldpay.offer.exceptionhandler.exception.OfferServiceBadRequest;

public final class RestPreconditions {
    static final String NULL_REQUEST_ELEMENT_MESSAGE = "Null request element detected";

    public static void checkIfBadRequest(final boolean expression, final String message) {
        if (!expression) {
            throw new OfferServiceBadRequest(message);
        }
    }

    public static <T> T checkRequestElementNotNull(final T requestElement) {
        if (requestElement == null) {
            throw new OfferServiceBadRequest(NULL_REQUEST_ELEMENT_MESSAGE);
        }
        return requestElement;
    }
}
