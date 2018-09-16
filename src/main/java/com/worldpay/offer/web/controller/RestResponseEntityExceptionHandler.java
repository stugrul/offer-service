package com.worldpay.offer.web.controller;

import com.worldpay.offer.exception.OfferServiceResourceNotFoundException;
import com.worldpay.offer.exception.api.ApiError;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DataIntegrityViolationException.class, OfferServiceResourceNotFoundException.class})
    protected final ResponseEntity<Object> handleBadRequest(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, message(HttpStatus.NOT_FOUND, ex), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private final ApiError message(final HttpStatus httpStatus, final Exception ex) {
        final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
        final String devMessage = ex.getClass().getSimpleName();

        return new ApiError(httpStatus.value(), message, devMessage);
    }
}
