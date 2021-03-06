package com.worldpay.offer.web.controller;

import com.worldpay.offer.service.CancelOfferService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CancelOfferController.class)
class CancelOfferControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CancelOfferService cancelOfferService;

    @Test
    void should_UpdateOffer() throws Exception {
        willDoNothing().given(cancelOfferService).cancel(1L);

        MediaType json = MediaType.parseMediaType("application/json;charset=UTF-8");

        mvc.perform(delete("/offers/1")
                            .contentType(json))
           .andExpect(status().isOk());

        verify(cancelOfferService).cancel(1L);
    }
}