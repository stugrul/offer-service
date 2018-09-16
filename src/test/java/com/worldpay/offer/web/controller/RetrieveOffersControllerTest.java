package com.worldpay.offer.web.controller;

import com.worldpay.offer.persistence.model.Offer;
import com.worldpay.offer.service.RetrieveOffersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static com.worldpay.offer.web.controller.util.TestUtil.getOffer;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RetrieveOffersController.class)
class RetrieveOffersControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RetrieveOffersService retrieveOffersService;

    @Test
    void should_FindOffers() throws Exception {
        Offer offer = getOffer();
        List<Offer> allOffers = Collections.singletonList(offer);

        given(retrieveOffersService.findAll()).willReturn(allOffers);
        MediaType json = MediaType.parseMediaType("application/json;charset=UTF-8");

        mvc.perform(get("/offers"))
           .andExpect(status().isOk())
           .andExpect(content().contentType(json))
           .andExpect(jsonPath("$", hasSize(1)))
           .andExpect(jsonPath("$[0].name", is(offer.getName())));

        verify(retrieveOffersService).findAll();
    }

    @Test
    void should_FindOffer_ById() throws Exception {
        Offer offer = getOffer();

        given(retrieveOffersService.findById(1L)).willReturn(offer);
        MediaType json = MediaType.parseMediaType("application/json;charset=UTF-8");

        mvc.perform(get("/offers/1"))
           .andExpect(status().isOk())
           .andExpect(content().contentType(json))
           .andExpect(jsonPath("$.name", is(offer.getName())));

        verify(retrieveOffersService).findById(1L);
    }
}