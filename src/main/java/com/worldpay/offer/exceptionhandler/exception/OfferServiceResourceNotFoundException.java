package com.worldpay.offer.exceptionhandler.exception;

public class OfferServiceResourceNotFoundException extends RuntimeException {

    public OfferServiceResourceNotFoundException(final String message) {
        super(message);
    }
}
