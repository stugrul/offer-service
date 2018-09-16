package com.worldpay.offer.web.controller;

import com.worldpay.offer.constant.Mappings;
import com.worldpay.offer.persistence.model.Offer;
import com.worldpay.offer.service.CreateOfferService;
import com.worldpay.offer.web.RestPreconditions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = Mappings.OFFERS)
public class CreateOfferController {

    private final CreateOfferService createOfferService;

    private CreateOfferController(final CreateOfferService createOfferService) {
        this.createOfferService = createOfferService;
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid final Offer offer) {

        RestPreconditions.checkRequestElementNotNull(offer);
        RestPreconditions.checkRequestElementNotNull(offer.getId());
        createOfferService.create(offer);
    }
}
