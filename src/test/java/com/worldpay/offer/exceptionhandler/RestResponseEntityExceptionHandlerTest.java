package com.worldpay.offer.exceptionhandler;

import com.worldpay.offer.exceptionhandler.exception.OfferServiceBadRequest;
import com.worldpay.offer.exceptionhandler.exception.OfferServiceResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
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
    void should_ReturnHandleServiceResourceNotFoundException() {
        ResponseEntity<Object> responseEntity = restResponseEntityExceptionHandler
                .handleResourceNotFound(new OfferServiceResourceNotFoundException(ERROR_MESSAGE),
                                        request);

        assertAll(
                () -> assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode()),
                () -> assertEquals(404, responseEntity.getStatusCodeValue())
        );
    }

    @Test
    void should_HandleOfferServiceBadRequestException() {
        ResponseEntity<Object> responseEntity = restResponseEntityExceptionHandler
                .handleBadRequest(new OfferServiceBadRequest(ERROR_MESSAGE),
                                  request);

        assertAll(
                () -> assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode()),
                () -> assertEquals(400, responseEntity.getStatusCodeValue())
        );
    }

    @Test
    void should_HandleDataIntegrityViolationException() {
        ResponseEntity<Object> responseEntity = restResponseEntityExceptionHandler
                .handleDataIntegrityViolationException(new DataIntegrityViolationException(ERROR_MESSAGE),
                                                       request);

        assertAll(
                () -> assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode()),
                () -> assertEquals(409, responseEntity.getStatusCodeValue())
        );
    }
}