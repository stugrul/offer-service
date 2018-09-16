package com.worldpay.offer.web.controller.retrieve;

import com.worldpay.offer.constant.Mappings;
import com.worldpay.offer.persistence.model.Offer;
import com.worldpay.offer.service.retrieve.RetrieveOffersService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = Mappings.OFFERS)
public class RetrieveOffersController {

    private RetrieveOffersService retrieveOffersService;

    private RetrieveOffersController(final RetrieveOffersService retrieveOffersService) {
        this.retrieveOffersService = retrieveOffersService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Offer> findAll() {

        return retrieveOffersService.findAll();
    }
}
