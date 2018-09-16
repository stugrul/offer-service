package com.worldpay.offer.web.controller;

import com.worldpay.offer.constant.Mappings;
import com.worldpay.offer.persistence.model.Offer;
import com.worldpay.offer.service.RetrieveOfferService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = Mappings.OFFERS)
public class RetrieveOfferController {

    private RetrieveOfferService retrieveOfferService;

    private RetrieveOfferController(final RetrieveOfferService retrieveOfferService) {
        this.retrieveOfferService = retrieveOfferService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Offer> findAll() {

        return retrieveOfferService.findAll();
    }
}
