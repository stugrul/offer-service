package com.worldpay.offer.web.controller;

import com.worldpay.offer.constant.Mappings;
import com.worldpay.offer.persistence.model.Offer;
import com.worldpay.offer.service.DeleteOfferService;
import com.worldpay.offer.web.RestPreConditions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = Mappings.OFFERS)
public class DeleteOfferController {

    private DeleteOfferService deleteOfferService;

    private DeleteOfferController(final DeleteOfferService deleteOfferService) {
        this.deleteOfferService = deleteOfferService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id, @RequestBody @Valid final Offer offer) {

        RestPreConditions.checkIfBadRequest(offer.getId().equals(id), "Offer ID and URI ID don't match");
        deleteOfferService.delete(id, offer);
    }
}
