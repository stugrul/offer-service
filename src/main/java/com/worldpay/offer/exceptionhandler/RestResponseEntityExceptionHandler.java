package com.worldpay.offer.exceptionhandler;

import com.worldpay.offer.exceptionhandler.api.ApiError;
import com.worldpay.offer.exceptionhandler.exception.OfferServiceBadRequest;
import com.worldpay.offer.exceptionhandler.exception.OfferServiceResourceNotFoundException;
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

    @ExceptionHandler(value = OfferServiceResourceNotFoundException.class)
    public final ResponseEntity<Object> handleResourceNotFound(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, message(HttpStatus.NOT_FOUND, ex), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public final ResponseEntity<Object> handleDataIntegrityViolationException(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, message(HttpStatus.CONFLICT, new RuntimeException("Offer name already exists or conflicted data provided")), new HttpHeaders(),
                                       HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = OfferServiceBadRequest.class)
    public final ResponseEntity<Object> handleBadRequest(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, message(HttpStatus.BAD_REQUEST, ex), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private final ApiError message(final HttpStatus httpStatus, final Exception ex) {
        final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
        final String devMessage = ex.getClass().getSimpleName();

        return new ApiError(httpStatus.value(), message, devMessage);
    }
}
