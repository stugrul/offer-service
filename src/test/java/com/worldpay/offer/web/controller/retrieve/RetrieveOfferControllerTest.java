package com.worldpay.offer.web.controller.retrieve;

import com.worldpay.offer.persistence.model.Offer;
import com.worldpay.offer.service.retrieve.RetrieveOfferService;
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

import static org.hamcrest.Matchers.is;
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
    void shouldFindOffer_ById() throws Exception {
        Offer offer = getOffer();

        given(retrieveOfferService.findById(1L)).willReturn(offer);
        MediaType json = MediaType.parseMediaType("application/json;charset=UTF-8");

        mvc.perform(get("/offers?id=1"))
           .andExpect(status().isOk())
           .andExpect(content().contentType(json))
           .andExpect(jsonPath("$.name", is(offer.getName())));

        verify(retrieveOfferService).findById(1L);
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