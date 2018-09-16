package com.worldpay.offer.web.controller;

import com.worldpay.offer.persistence.model.Offer;
import com.worldpay.offer.service.CreateOfferService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.worldpay.offer.web.controller.util.TestUtil.asJsonString;
import static com.worldpay.offer.web.controller.util.TestUtil.getOffer;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CreateOfferController.class)
class CreateOfferControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CreateOfferService createOfferService;

    @Test
    void should_CreateOffer() throws Exception {
        Offer offer = getOffer();

        willDoNothing().given(createOfferService).create(offer);
        MediaType json = MediaType.parseMediaType("application/json;charset=UTF-8");

        mvc.perform(post("/offers")
                            .contentType(json)
                            .content(asJsonString(offer)))
           .andExpect(status().isCreated());
    }
}