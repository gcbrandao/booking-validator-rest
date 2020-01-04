package com.gcbrandao.bookingvalidatorrest.service;

import com.gcbrandao.bookingvalidatorrest.model.BookingInfo;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

@Service
public class BookingService {

    public  void main(String[] args) {


//        // valida o formato da strind eh valido
//        if(formatValidation(strCheckin, strCheckout)){
//            // transforma a string em LocalDate
//            checkin = string2LocalDate(strCheckin);
//            checkout = string2LocalDate(strCheckout);
//        } else {
//            System.out.println("Formato de data invalido!! As datas devem ter o formato de YYYY-MM-DD");
//            System.exit(0);
//        }
//
//        // valida se o checkIn é maior que o checkout
//        if(checkin.isBefore(checkout)){
//            //conta as semanas e dias adicionais
//            //bookingInfo = countWeeks(checkin, checkout);
//        } else {
//            System.out.println("Data de checkIn deve ser anterior a data de checkout!!");
//            System.exit(0);
//        }
//
//        System.out.println(bookingInfo.toString());
    }

    /**
     * Metodo que verifica a diferença entre duas datas informadas e retorna em numero de semnas e dias extra.
     * 1 - Verifica se as datas sao menos que uma semana e completa a semana baseada no domingo
     *
     *
     *
     * Retorna o objeto BookingInfo que contem as informações necessarias

     * @return BookingInfo
     */
    public  BookingInfo getWeeksAndExtraNigths(BookingInfo bookingInfo){

        LocalDate checkIn = bookingInfo.getCheckin();
        LocalDate checkOut = bookingInfo.getCheckout();

        Integer weeks = 0;
        Integer daysAfter = 0;
        Integer daysBefore = 0;


        int days = (int) ChronoUnit.DAYS.between(checkIn, checkOut);


        // verifica dias extra
        Integer extraDays = days % 7;

        // viagem curta
        if(days <= 7){
            return shortTrip(bookingInfo);
        }

        // SE NAO FOR NEM SABADO E NEM DOMINGO
        if (!checkIn.getDayOfWeek().equals(DayOfWeek.SUNDAY) &&
                !checkIn.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {

            // inicio = segunda ou terca -> semana de domingo a domingo
            if (checkIn.getDayOfWeek().equals(DayOfWeek.MONDAY) ||
                    checkIn.getDayOfWeek().equals(DayOfWeek.TUESDAY)) {

                // volta checkin para DOMINGO anterior
                while (!checkIn.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                    checkIn = checkIn.minusDays(1);
                }
//                period = Period.between(checkIn, checkOut);
//                days = period.getDays();

                days = (int)ChronoUnit.DAYS.between(checkIn, checkOut);

                // numero de semanas
                weeks = days / 7;
                daysBefore = 0;
                if(extraDays > 4) {
                    weeks++;
                } else {
                    daysAfter = extraDays;
                }

            } else { // inicio = quarta ou quinta ou sexta -> semana de sabado a sabado
                // volta checkin para SABADO anterior

//                while (!checkIn.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
//                    checkIn = checkIn.plusDays(1);
//                }

                days = (int)ChronoUnit.DAYS.between(checkIn, checkOut);
                // numero de semanas
                weeks = days / 7;
                daysAfter = 0;
                extraDays = days % 7;

                if(extraDays > 4) {
                    weeks++;
                } else {
                    daysBefore = extraDays;
                }

            }

        } else { // SE FOR SABADO OU DOMINGO

//                period = Period.between(checkIn, checkOut);
//                days = period.getDays();

            days = (int)ChronoUnit.DAYS.between(checkIn, checkOut);
            // numero de semanas
            weeks = days / 7;
            daysBefore = 0;
            if(extraDays > 4) {
                weeks++;
            } else {
                daysAfter = extraDays;
            }

        }


        // calculo final para retorno
        //weeks = days / 7;
        //daysAfter = days % 7;


        return new BookingInfo(weeks,daysAfter,daysBefore, checkIn, checkOut);
    }


    // viagem curta
    public BookingInfo  shortTrip(BookingInfo bookingInfo){

        Integer days = (int)ChronoUnit.DAYS.between(bookingInfo.getCheckin(), bookingInfo.getCheckout());

        Integer weeks = 0;
        Integer daysBefore = 0;
        Integer daysAfter = 0;

        if(days >= 4){
            weeks = 1;
        } else {
            daysBefore = days;
        }
        return new BookingInfo(weeks, daysAfter, daysBefore, null, null);
    }



    /**
     * Metodo que usa regular expressoin para validar o formato da data.
     * Os valores de entrada devem estar sempre no formado YYYY-MM-DD
     * @param checkin
     * @param checkout
     * @return boolean
     */
    public static boolean formatValidation(String checkin, String checkout){

        final String PATTER_FORMAT = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";

        return (checkin.matches(PATTER_FORMAT) && checkout.matches(PATTER_FORMAT));

    }

    /**
     * Metodo que recebe a String com a data e retorna um LocalDate
     * @param strDate
     * @return LocalDate
     */
    public static LocalDate string2LocalDate(String strDate){
        final String  DELIMITADOR_DATA = "-";
        String[] tokens = strDate.split(DELIMITADOR_DATA);

        return  LocalDate.of(Integer.valueOf(tokens[0]),Integer.valueOf(tokens[1]),Integer.valueOf(tokens[2]));
    }
}
