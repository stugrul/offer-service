package com.worldpay.offer.web.controller;

import com.worldpay.offer.exception.OfferServiceResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RestResponseEntityExceptionHandlerTest {

    private static final String ERROR_MESSAGE = "Error Message";

    @InjectMocks
    private RestResponseEntityExceptionHandler restResponseEntityExceptionHandler;

    @Mock
    private WebRequest request;

    @Test
    void shouldReturnOfferServiceResourceNotFoundException() {
        ResponseEntity<Object> responseEntity = restResponseEntityExceptionHandler
                .handleBadRequest(new OfferServiceResourceNotFoundException(ERROR_MESSAGE),
                                  request);

        assertAll(
                () -> assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode()),
                () -> assertEquals(404, responseEntity.getStatusCodeValue())
        );
    }
}