package com.worldpay.offer.web.controller;

import com.worldpay.offer.persistence.model.Offer;
import com.worldpay.offer.service.RetrieveOfferService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RetrieveOfferController.class)
class RetrieveOfferControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RetrieveOfferService retrieveOfferService;

    @Test
    void shouldGetOffers_thenReturnJsonArray() throws Exception {
        Offer offer = getOffer();
        List<Offer> allOffers = Collections.singletonList(offer);

        given(retrieveOfferService.findAll()).willReturn(allOffers);
        MediaType json = MediaType.parseMediaType("application/json;charset=UTF-8");

        mvc.perform(get("/offers"))
           .andExpect(status().isOk())
           .andExpect(content().contentType(json))
           .andExpect(jsonPath("$", hasSize(1)))
           .andExpect(jsonPath("$[0].name", is(offer.getName())));

        verify(retrieveOfferService).findAll();
    }

    private static Offer getOffer() {
        Offer offer = new Offer();

        offer.setName("Garfield");
        offer.setCurrency("EUR");
        offer.setDescription("This is a Garfield toy");
        offer.setId(1L);
        offer.setPrice(BigDecimal.valueOf(2));
        offer.setValidUntil(LocalDateTime.of(LocalDate.of(2018, Month.DECEMBER, 31), LocalTime.MIDNIGHT));

        return offer;
    }
}