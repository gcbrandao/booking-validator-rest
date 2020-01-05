package com.gcbrandao.bookingvalidatorrest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gcbrandao.bookingvalidatorrest.model.BookingInfo;
import com.gcbrandao.bookingvalidatorrest.model.BookingReturn;
import com.gcbrandao.bookingvalidatorrest.service.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    void bookJustExtraNights() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-03");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getDaysBefore()).isEqualTo(1);
    }

    @Test
    void case1() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-03");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(0);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(0);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(1);
    }

    @Test
    void case2() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-04");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(0);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(0);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case3() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-06");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(1);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(0);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(0);
    }

    @Test
    void case4() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-05");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(0);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(0);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(3);
    }

    @Test
    void case5() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-07");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(1);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(0);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(0);
    }

    @Test
    void case6() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-08");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(1);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(0);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(0);
    }

    @Test
    void case7() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-09");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(1);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(0);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case8() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-10");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(1);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(0);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case9() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-11");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(1);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(0);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case10() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-12");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(1);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(1);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case11() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-13");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(1);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(2);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case12() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-14");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(1);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(3);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case13() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-15");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(2);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(0);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case14() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-16");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(2);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(0);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case15() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-17");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(2);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(0);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case16() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-18");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(2);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(0);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case17() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-19");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(2);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(1);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case18() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-20");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(2);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(2);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case19() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-21");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(2);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(3);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case20() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-22");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(3);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(0);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case21() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-23");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(3);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(0);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case22() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-24");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(3);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(0);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case23() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-25");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(3);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(0);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case24() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-26");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(3);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(1);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case25() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-27");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(3);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(2);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case26() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-28");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(3);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(3);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case27() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-29");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(4);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(0);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case28() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-30");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(4);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(0);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case29() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-31");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(4);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(0);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(2);
    }

    @Test
    void case30() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-07");
        LocalDate checkOut = string2LocalDate("2020-01-20");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(2);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(1);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(0);
    }

    @Test
    void case31() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-05");
        LocalDate checkOut = string2LocalDate("2020-01-14");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(1);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(2);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(0);
    }

    @Test
    void case32() throws Exception {
        LocalDate checkIn = string2LocalDate("2020-01-08");
        LocalDate checkOut = string2LocalDate("2020-01-31");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        mockMvc.perform(post("/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookingInfo)))
                .andExpect(status().isOk());

        BookingReturn bookReturn = service.getWeeksAndExtraNigths(bookingInfo);


        assertThat(bookReturn.getWeeks()).isEqualTo(3);
        assertThat(bookReturn.getDaysAfter()).isEqualTo(0);
        assertThat(bookReturn.getDaysBefore()).isEqualTo(3);
    }


    @Test
    void validateGetWeeks() throws Exception {

        LocalDate checkIn = string2LocalDate("2020-01-02");
        LocalDate checkOut = string2LocalDate("2020-01-15");

        BookingInfo bookingInfo = new BookingInfo(checkIn, checkOut);

        Integer weeks = service.getWeeks(bookingInfo);

        // case2
        LocalDate checkIn2 = string2LocalDate("2020-01-02");
        LocalDate checkOut2 = string2LocalDate("2020-01-22");

        BookingInfo bookingInfo2 = new BookingInfo(checkIn2, checkOut2);

        Integer weeks2 = service.getWeeks(bookingInfo2);

        assertThat(weeks).isEqualTo(1);
        assertThat(weeks2).isEqualTo(2);

    }

    @Test
    void validateGetNightsBefore() throws Exception {

        // dias da semana
        LocalDate segunda = string2LocalDate("2020-01-06");
        LocalDate terca = string2LocalDate("2020-01-07");
        LocalDate quarta = string2LocalDate("2020-01-08");
        LocalDate quinta = string2LocalDate("2020-01-09");
        LocalDate sexta = string2LocalDate("2020-01-10");
        LocalDate sabado = string2LocalDate("2020-01-11");
        LocalDate domingo = string2LocalDate("2020-01-12");

        // dia base da semana de calculo
        DayOfWeek dayOfWeekSeg = service.getBaseDayOfWeek(segunda);
        DayOfWeek dayOfWeekTer = service.getBaseDayOfWeek(terca);
        DayOfWeek dayOfWeekQua = service.getBaseDayOfWeek(quarta);
        DayOfWeek dayOfWeekQui = service.getBaseDayOfWeek(quinta);
        DayOfWeek dayOfWeekSex = service.getBaseDayOfWeek(sexta);
        DayOfWeek dayOfWeekSab = service.getBaseDayOfWeek(sabado);
        DayOfWeek dayOfWeekDom = service.getBaseDayOfWeek(domingo);

        // sevice noites extra
        int seg = service.getNightsBefore(segunda, dayOfWeekSeg); // 0
        int ter = service.getNightsBefore(terca, dayOfWeekTer); // 0
        int qua = service.getNightsBefore(quarta, dayOfWeekQua); // 3
        int qui = service.getNightsBefore(quinta, dayOfWeekQui); // 2
        int sex = service.getNightsBefore(sexta, dayOfWeekSex); // 1
        int sab = service.getNightsBefore(sabado, dayOfWeekSab); // 0
        int dom = service.getNightsBefore(domingo, dayOfWeekDom); // 0


        //
        assertThat(seg).isEqualTo(0);
        assertThat(ter).isEqualTo(0);
        assertThat(qua).isEqualTo(3);
        assertThat(qui).isEqualTo(2);
        assertThat(sex).isEqualTo(1);
        assertThat(sab).isEqualTo(0);
        assertThat(dom).isEqualTo(0);
    }

    @Test
    void validateGetNightsAfter() throws Exception {

        // dias da semana
        LocalDate segunda = string2LocalDate("2020-01-06");
        LocalDate terca = string2LocalDate("2020-01-07");
        LocalDate quarta = string2LocalDate("2020-01-08");
        LocalDate quinta = string2LocalDate("2020-01-09");
        LocalDate sexta = string2LocalDate("2020-01-10");
        LocalDate sabado = string2LocalDate("2020-01-11");
        LocalDate domingo = string2LocalDate("2020-01-12");

        // dia base da semana de calculo
        // segunda ou terça, contar semanas de domingo em domingo
        // quarta-feira, quinta-feira ou sexta-feira, contar semanas de sábado em sábado

        DayOfWeek baseDaySabado = DayOfWeek.SATURDAY;
        DayOfWeek baseDayDomingo = DayOfWeek.SUNDAY;


        //sabado
        assertThat(service.getNightsAfter(segunda, baseDaySabado)).isEqualTo(2);
        assertThat(service.getNightsAfter(terca, baseDaySabado)).isEqualTo(3);
        assertThat(service.getNightsAfter(quarta, baseDaySabado)).isEqualTo(4);
    }


    @Test
    void validateBaseDayofWeek() throws Exception {


        LocalDate segunda = string2LocalDate("2020-01-06");
        LocalDate terca = string2LocalDate("2020-01-07");
        LocalDate quarta = string2LocalDate("2020-01-08");
        LocalDate quinta = string2LocalDate("2020-01-09");
        LocalDate sexta = string2LocalDate("2020-01-10");
        LocalDate sabado = string2LocalDate("2020-01-11");
        LocalDate domingo = string2LocalDate("2020-01-12");

        DayOfWeek dayOfWeekSeg = service.getBaseDayOfWeek(segunda);
        DayOfWeek dayOfWeekTer = service.getBaseDayOfWeek(terca);
        DayOfWeek dayOfWeekQua = service.getBaseDayOfWeek(quarta);
        DayOfWeek dayOfWeekQui = service.getBaseDayOfWeek(quinta);
        DayOfWeek dayOfWeekSex = service.getBaseDayOfWeek(sexta);
        DayOfWeek dayOfWeekSab = service.getBaseDayOfWeek(sabado);
        DayOfWeek dayOfWeekDom = service.getBaseDayOfWeek(domingo);

        //
        assertThat(dayOfWeekSeg).isEqualTo(DayOfWeek.SUNDAY);
        assertThat(dayOfWeekTer).isEqualTo(DayOfWeek.SUNDAY);
        assertThat(dayOfWeekQua).isEqualTo(DayOfWeek.SATURDAY);
        assertThat(dayOfWeekQui).isEqualTo(DayOfWeek.SATURDAY);
        assertThat(dayOfWeekSex).isEqualTo(DayOfWeek.SATURDAY);
        assertThat(dayOfWeekSab).isEqualTo(DayOfWeek.SATURDAY);
        assertThat(dayOfWeekDom).isEqualTo(DayOfWeek.SUNDAY);

    }

    @Test
    void validateFullWeeks() {
        LocalDate checkIn1 = string2LocalDate("2020-01-02");
        // checkin quinta = sabado em sabado apos checkin
        BookingInfo bookingInfo1 = service.getFullWeeks(checkIn1, 2);

        LocalDate checkIn2 = string2LocalDate("2020-01-06");
        // segunda ou terça, contar  de domingo em domingo a partir do primeiro domingo anterior ao check-in
        BookingInfo bookingInfo2 = service.getFullWeeks(checkIn2, 4);


        assertThat(bookingInfo1.getCheckin()).isEqualTo(LocalDate.of(2020, 01, 04));
        assertThat(bookingInfo1.getCheckout()).isEqualTo(LocalDate.of(2020, 01, 18));

        assertThat(bookingInfo2.getCheckin()).isEqualTo(LocalDate.of(2020, 01, 05));
        assertThat(bookingInfo2.getCheckout()).isEqualTo(LocalDate.of(2020, 02, 02));

    }

    public LocalDate string2LocalDate(String strDate) {
        final String DELIMITADOR_DATA = "-";
        String[] tokens = strDate.split(DELIMITADOR_DATA);

        return LocalDate.of(Integer.valueOf(tokens[0]), Integer.valueOf(tokens[1]), Integer.valueOf(tokens[2]));
    }
}
