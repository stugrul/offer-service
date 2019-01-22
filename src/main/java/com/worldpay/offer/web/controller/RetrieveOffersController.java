package com.worldpay.offer.web.controller;

import com.worldpay.offer.constant.Mappings;
import com.worldpay.offer.persistence.model.Offer;
import com.worldpay.offer.service.RetrieveOffersService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Offer> findAll() {

        return retrieveOffersService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Offer findById(@PathVariable Long id) {

        return retrieveOffersService.findById(id);
    }
}
