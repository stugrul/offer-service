package com.worldpay.offer.web;

import com.worldpay.offer.exceptionhandler.exception.OfferServiceBadRequest;

public final class RestPreConditions {
    public static void checkIfBadRequest(final boolean expression, final String message) {
        if (!expression) {
            throw new OfferServiceBadRequest(message);
        }
    }
}
