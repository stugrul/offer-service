package com.worldpay.offer.exceptionhandler.exception;

public class OfferServiceBadRequest extends RuntimeException {
    public OfferServiceBadRequest(final String message) {
        super(message);
    }
}
