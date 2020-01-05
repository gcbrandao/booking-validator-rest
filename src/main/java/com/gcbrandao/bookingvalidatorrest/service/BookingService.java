package com.gcbrandao.bookingvalidatorrest.service;

import com.gcbrandao.bookingvalidatorrest.model.BookingInfo;
import com.gcbrandao.bookingvalidatorrest.model.BookingReturn;
import com.gcbrandao.bookingvalidatorrest.service.exception.BookingException;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static java.time.DayOfWeek.*;

@Service
public class BookingService {

    /**
     * Metodo que verifica a diferença entre duas datas informadas e retorna em numero de semnas e dias extra.
     * 1 - Verifica se as datas sao menos que uma semana e completa a semana baseada no domingo
     * <p>
     * <p>
     * <p>
     * Retorna o objeto BookingInfo que contem as informações necessarias
     *
     * @return BookingInfo
     */
    public BookingReturn getWeeksAndExtraNigths(BookingInfo bookingInfo) {

        LocalDate checkIn = bookingInfo.getCheckin();
        LocalDate checkOut = bookingInfo.getCheckout();

        Integer weeks = 0;
        Integer daysAfter = 0;
        Integer daysBefore = 0;

        if (checkIn.isAfter(checkOut)) {
            throw new BookingException("Data de check in [" + checkIn + "] deve ser anterior a data de check out [" + checkOut + "]");
        }

        int totalDays = (int) ChronoUnit.DAYS.between(checkIn, checkOut);

        // viagem curta eh menos que 7
        if (totalDays < 7) {
            return shortTrip(bookingInfo);
        }

        weeks = getWeeks(bookingInfo);

        BookingInfo fullWeeksBookingInfo = getFullWeeks(checkIn, weeks);

        // se a data de check in for antes da fullWeek calcula noitesExtra antes
        if (checkIn.isBefore(fullWeeksBookingInfo.getCheckin())) {
            daysBefore = getNightsBefore(checkIn, getBaseDayOfWeek(checkIn));
            if (daysBefore >= 4) {
                daysBefore = 0;
                weeks++;
            }
        }
        // se a data do checkin for depois do fullWeek e  for seg ou ter add uma semana
        if (checkIn.isAfter(fullWeeksBookingInfo.getCheckin())
                && (checkIn.getDayOfWeek().equals(MONDAY) || checkIn.getDayOfWeek().equals(TUESDAY))) {
            weeks++;
        }

        // se o checkOut for depois do fim da full week calcula as noites extra depois
        if (checkOut.isAfter(fullWeeksBookingInfo.getCheckout())) {
            daysAfter = getNightsAfter(checkOut, getBaseDayOfWeek(checkIn));
            if (daysAfter >= 4) {
                daysAfter = 0;
                weeks++;
            }
        }

        return new BookingReturn(weeks, daysAfter, daysBefore);
    }

    // retorna o numero total de semanas no periodo considerando arredondamento
    public Integer getWeeks(BookingInfo bookingInfo) {

        Integer weeks = 0;
        Integer totalDays = (int) ChronoUnit.DAYS.between(bookingInfo.getCheckin(), bookingInfo.getCheckout());


        weeks = totalDays / 7;

        return weeks;
    }

    // retorna a semana cheia sem considerar as noites extra
    public BookingInfo getFullWeeks(LocalDate checkIn, Integer weeks) {
        // calculo de semanas cheias

        LocalDate fullCheckIn = checkIn;
        LocalDate fullCheckOut = checkIn;

        //segunda ou terça, contar  de domingo em domingo a partir do primeiro domingo anterior ao check-in
        if (fullCheckIn.getDayOfWeek().equals(MONDAY) || fullCheckIn.getDayOfWeek().equals(TUESDAY)) {
            while (!fullCheckIn.getDayOfWeek().equals(SUNDAY)) {
                fullCheckIn = fullCheckIn.minusDays(1L);
            }
        }
        // quarta quinta ou sexta, semanas de sábado em sábado a partir do primeiro sábado após ao check-in
        if (fullCheckIn.getDayOfWeek().equals(WEDNESDAY) || fullCheckIn.getDayOfWeek().equals(THURSDAY) || fullCheckIn.getDayOfWeek().equals(FRIDAY)) {
            while (!fullCheckIn.getDayOfWeek().equals(SATURDAY)) {
                fullCheckIn = fullCheckIn.plusDays(1L);
            }
        }

        fullCheckOut = fullCheckIn.plusWeeks(weeks);

        return new BookingInfo(fullCheckIn, fullCheckOut);
    }


    // viagem curta
    public BookingReturn shortTrip(BookingInfo bookingInfo) {

        Integer days = (int) ChronoUnit.DAYS.between(bookingInfo.getCheckin(), bookingInfo.getCheckout());

        DayOfWeek dayOfWeek = bookingInfo.getCheckin().getDayOfWeek();

        Integer weeks = 0;
        Integer daysBefore = 0;
        Integer daysAfter = 0;

        if (days >= 4) {
            weeks = 1;
            daysBefore = 0;
            daysAfter = 0;
        } else {
            weeks = 0;
            daysBefore = days;
            daysAfter = 0;
        }
        return new BookingReturn(weeks, daysAfter, daysBefore);
    }


    // calcula noites extra antes de acordo com o dia da semana
    public int getNightsBefore(LocalDate checkin, DayOfWeek baseDayOfWeek) {

        int extraNightsBefore = 0;

        DayOfWeek checkinDayOfWeek = checkin.getDayOfWeek();

        if (checkinDayOfWeek.equals(WEDNESDAY) || checkinDayOfWeek.equals(THURSDAY) ||
                checkinDayOfWeek.equals(FRIDAY)) {

            while (!checkinDayOfWeek.equals(baseDayOfWeek)) {
                extraNightsBefore++;
                checkinDayOfWeek = checkinDayOfWeek.plus(1L);
            }
        }
        return extraNightsBefore;
    }


    // calcula noites extra no fim de acordo com a data do checkout
    //-Se for de sábado em sábado, contar noites extras a partir do último sábado até o check-out.
    public int getNightsAfter(LocalDate checkout, DayOfWeek baseDayOfWeek) {

        int extraNightsAfter = 0;

        DayOfWeek checkOutDayOfWeek = checkout.getDayOfWeek();


        while (!checkOutDayOfWeek.equals(baseDayOfWeek)) {
            extraNightsAfter++;
            checkOutDayOfWeek = checkOutDayOfWeek.minus(1L);
        }
        return extraNightsAfter;
    }

    // retorna dia da semana base de calculo de acordo a data de check in
    // segunda ou terça, contar semanas de domingo em domingo
    // quarta-feira, quinta-feira ou sexta-feira, contar semanas de sábado em sábado
    public DayOfWeek getBaseDayOfWeek(LocalDate checkin) {

        DayOfWeek baseDayOfWeek = DayOfWeek.SUNDAY;
        DayOfWeek checkinDayOfWeek = checkin.getDayOfWeek();

        switch (checkinDayOfWeek) {
            case SATURDAY:
                return SATURDAY;
            case SUNDAY:
                return SUNDAY;
            case MONDAY:
                return SUNDAY;
            case TUESDAY:
                return SUNDAY;
            case WEDNESDAY:
                return SATURDAY;
            case THURSDAY:
                return SATURDAY;
            case FRIDAY:
                return SATURDAY;
        }
        return baseDayOfWeek;
    }
}
