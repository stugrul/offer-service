package com.worldpay.offer.web.controller;

import com.worldpay.offer.persistence.model.Offer;
import com.worldpay.offer.service.UpdateOfferService;
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
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UpdateOfferController.class)
class UpdateOfferControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UpdateOfferService updateOfferService;

    @Test
    void should_UpdateOffer() throws Exception {
        Offer offer = getOffer();

        willDoNothing().given(updateOfferService).update(1L, offer);

        MediaType json = MediaType.parseMediaType("application/json;charset=UTF-8");

        mvc.perform(put("/offers/1", offer.getId())
                            .contentType(json)
                            .content(asJsonString(offer)))
           .andExpect(status().isOk());

        verify(updateOfferService).update(1L, offer);
    }
}