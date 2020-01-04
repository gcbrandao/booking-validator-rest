package com.gcbrandao.bookingvalidatorrest;


import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gcbrandao.bookingvalidatorrest.model.BookingInfo;
import com.gcbrandao.bookingvalidatorrest.service.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
class BookingValidatorRestApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private BookingService service;

	@Test
	void contextLoads() {
	}

	@Test
	void bookJustExtraNights() throws Exception{
		LocalDate checkIn  = string2LocalDate("2020-01-10");
		LocalDate checkOut = string2LocalDate("2020-01-11");

		BookingInfo bookingInfo = new BookingInfo(0, 0, 0, checkIn, checkOut);

		mockMvc.perform(post("/bookings")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(bookingInfo)))
				.andExpect(status().isOk());

		BookingInfo bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


		assertThat(bookReturn.getDaysBefore()).isEqualTo(1);
	}



	public LocalDate string2LocalDate(String strDate){
		final String  DELIMITADOR_DATA = "-";
		String[] tokens = strDate.split(DELIMITADOR_DATA);

		return  LocalDate.of(Integer.valueOf(tokens[0]),Integer.valueOf(tokens[1]),Integer.valueOf(tokens[2]));
	}
}
