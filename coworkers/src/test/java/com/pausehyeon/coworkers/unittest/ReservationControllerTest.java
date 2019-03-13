package com.pausehyeon.coworkers.unittest;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.pausehyeon.coworkers.api.reservation.PostReservationValidator;
import com.pausehyeon.coworkers.api.reservation.PutReservationValidator;
import com.pausehyeon.coworkers.api.reservation.ReservationController;
import com.pausehyeon.coworkers.api.reservation.ReservationService;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
@AutoConfigureRestDocs
public class ReservationControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private ReservationService service;
    
    @MockBean
    private PostReservationValidator postReservationValidator;
    
    @MockBean
    private PutReservationValidator putReservationValidator;
    
    @Test
    public void whenGetReservations_thenReturnJsonArray() throws Exception{
    	mvc.perform(get("/reservations").contentType(MediaType.APPLICATION_JSON))
    	.andExpect(status().isOk())
    	.andExpect(status().is2xxSuccessful())
    	.andDo(document("reservations"));
    }
    
    @Test
    public void givenWrongRid_whenGetReservation_thenReturn204() throws Exception{
    	mvc.perform(get("/reservation/999").contentType(MediaType.APPLICATION_JSON))
    	.andExpect(status().isOk())
    	.andExpect(status().is2xxSuccessful())
    	.andDo(document("reservations"));
    }

    @Test
    public void givenWrongFormatRid_whenGetReservation_thenReturn400() throws Exception{
    	mvc.perform(get("/reservation/가나다").contentType(MediaType.APPLICATION_JSON))
    	.andExpect(status().is4xxClientError())
    	.andDo(document("reservations"));
    }
    
    @Test
    public void givenRid_whenGetReservation_thenReturnJson() throws Exception{
    	mvc.perform(get("/reservation/1").contentType(MediaType.APPLICATION_JSON))
    	.andExpect(status().isOk())
    	.andExpect(status().is2xxSuccessful())
    	.andDo(document("reservations"));
    }
    
    @Test
    public void whenPostReservation_thenReturnJson() throws Exception{
    	mvc.perform(post("/reservation")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content("{\"mid\": 1"
    					+ ", \"title\": \"통합회원제회의\""
    					+ ", \"userName\": \"정지현\""
    					+ ", \"pin\": \"3942\""
    					+ ", \"start\": \"201903141800\""
    					+ ", \"end\": \"201903141830\""
    					+ ", \"isRepeated\": false"
    					+ ", \"firstRegisteredThrough\": \"WEB001\"}"))
    	.andExpect(status().isOk())
    	.andExpect(status().is2xxSuccessful());
    }
    
    @Test
    public void whenPostRepeatedReservation_thenReturnJson() throws Exception{
    	mvc.perform(post("/reservation")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content("{\"mid\": 1"
    					+ ", \"title\": \"통합회원제회의\""
    					+ ", \"userName\": \"정지현\""
    					+ ", \"pin\": \"3942\""
    					+ ", \"start\": \"201903141800\""
    					+ ", \"end\": \"201903141830\""
    					+ ", \"isRepeated\": true"
    					+ ", \"repeatInterval\": 7"
    					+ ", \"repeatCount\": 3"
    					+ ", \"firstRegisteredThrough\": \"WEB001\"}"))
    	.andExpect(status().isOk())
    	.andExpect(status().is2xxSuccessful());
    }
    
    @Test
    public void givenEmptyRid_whenPostReservation_thenReturnJson() throws Exception{
    	mvc.perform(post("/reservation")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content("{\"mid\": 1"
    					+ ", \"title\": \"통합회원제회의\""
    					+ ", \"userName\": \"정지현\""
    					+ ", \"pin\": \"3942\""
    					+ ", \"start\": \"201903141800\""
				    	+ ", \"isRepeated\": false"
				    	+ ", \"firstRegisteredThrough\": \"WEB001\"}"))
    	.andExpect(status().isOk())
    	.andExpect(status().is2xxSuccessful());
    }
    
    /*
    @Test
    public void givenEmptyMid_whenPostReservation_thenReturn400() throws Exception{
    	mvc.perform(post("/reservation")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content("{\"title\": \"통합회원제회의\""
    					+ ", \"userName\": \"정지현\""
    					+ ", \"pin\": \"3942\""
    					+ ", \"start\": \"201903141800\""
    					+ ", \"isRepeated\": false"
    					+ ", \"firstRegisteredThrough\": \"WEB001\"}"))
    	.andExpect(status().is4xxClientError());
    }
    */
    
    @Test
    public void givenWrongDate_whenPostReservation_thenReturn400() throws Exception{
    	mvc.perform(post("/reservation")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content("{\"mid\": 1"
    					+ ", \"title\": \"통합회원제회의\""
    					+ ", \"userName\": \"정지현\""
    					+ ", \"pin\": \"3942\""
    					+ ", \"start\": \"2019031418!!\""
    					+ ", \"end\": \"201903141830\""
    					+ ", \"isRepeated\": false"
    					+ ", \"firstRegisteredThrough\": \"WEB001\"}"))
    	.andExpect(status().is4xxClientError());
    } 
    
    /*
    @Test
    public void givenWrongEnd_whenPostReservation_thenReturn400() throws Exception{
    	mvc.perform(post("/reservation")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content("{\"mid\": 1"
    					+ ", \"title\": \"통합회원제회의\""
    					+ ", \"userName\": \"정지현\""
    					+ ", \"pin\": \"3942\""
    					+ ", \"start\": \"201903141800\""
    					+ ", \"end\": \"201903131830\""
    					+ ", \"isRepeated\": false"
    					+ ", \"firstRegisteredThrough\": \"WEB001\"}"))
    	.andExpect(status().is4xxClientError());
    }    
    */
    
    @Test
    public void givenEmptyRid_whenPutReservation_thenReturn400() throws Exception{
    	mvc.perform(put("/reservation")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content("{\"rid\": 1"
    					+"\"mid\": 1"
    					+ ", \"title\": \"통합회원제회의\""
    					+ ", \"pin\": \"3942\""
    					+ ", \"start\": \"201903141800\""
    					+ ", \"end\": \"201903141830\""
    					+ ", \"isRepeated\": false"
    					+ ", \"lastModifiedThrough\": \"WEB001\"}"))
    	.andExpect(status().is4xxClientError());
    }

    /*
    @Test
    public void givenEmptyRid_whenPutReservation_thenReturn400() throws Exception{
    	mvc.perform(put("/reservation")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content("{\"mid\": 1"
    					+ ", \"title\": \"통합회원제회의\""
    					+ ", \"pin\": \"3942\""
    					+ ", \"start\": \"201903141800\""
    					+ ", \"end\": \"201903141830\""
    					+ ", \"isRepeated\": false"
    					+ ", \"lastModifiedThrough\": \"WEB001\"}"))
    	.andExpect(status().is4xxClientError());
    }
    */
}
