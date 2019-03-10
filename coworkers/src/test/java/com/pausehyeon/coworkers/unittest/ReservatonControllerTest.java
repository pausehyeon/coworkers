package com.pausehyeon.coworkers.unittest;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.pausehyeon.coworkers.controllers.ReservationController;
import com.pausehyeon.coworkers.services.ReservationService;
import com.pausehyeon.coworkers.services.ResponseCodeService;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
@AutoConfigureRestDocs
public class ReservatonControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private ReservationService service;
    
    @MockBean
    private ResponseCodeService responseService;
    
    @Test
    public void whenGetReservations_thenReturnJsonArray() throws Exception{
    	mvc.perform(get("/reservations").contentType(MediaType.APPLICATION_JSON))
    	.andExpect(status().isOk())
    	.andExpect(status().is2xxSuccessful())
    	.andDo(document("reservations"));
    }
}
