package com.worldpay.offer.web.controller;

import com.worldpay.offer.constant.Mappings;
import com.worldpay.offer.persistence.model.Offer;
import com.worldpay.offer.service.RetrieveOffersService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = Mappings.OFFERS)
public class RetrieveOffersController {

    private final RetrieveOffersService retrieveOffersService;

    private RetrieveOffersController(final RetrieveOffersService retrieveOffersService) {
        this.retrieveOffersService = retrieveOffersService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Offer> findAll() {

        return retrieveOffersService.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/{id}")
    @ResponseBody
    public Offer findById(@PathVariable String id) {

        return retrieveOffersService.findById(Long.valueOf(id));
    }
}
