package com.worldpay.offer.web.controller.retrieve;

import com.worldpay.offer.constant.Mappings;
import com.worldpay.offer.persistence.model.Offer;
import com.worldpay.offer.service.retrieve.RetrieveOfferService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Mappings.OFFERS)
public class RetrieveOfferController {

    private RetrieveOfferService retrieveOfferService;

    private RetrieveOfferController(final RetrieveOfferService retrieveOfferService) {
        this.retrieveOfferService = retrieveOfferService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Offer findById(@RequestParam("id") final Long id) {

        return retrieveOfferService.findById(id);
    }
}
