package com.gcbrandao.bookingvalidatorrest;

import com.gcbrandao.bookingvalidatorrest.model.BookingInfo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class BookingControl {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String checkIn, checkOut;

        System.out.printf("Informe as datas no formato YYYY-MM-DD :\n");

        System.out.printf("Informe a data de check in:\n");
        checkIn = scanner.next();

        System.out.printf("Informe a data de check out:\n");
        checkOut = scanner.next();


        // criando as datas em string
        //String strCheckin  = "2020-01-09";
        String strCheckin  = checkIn;
        System.out.println("DATA CHECK-IN: " + strCheckin);

        //String strCheckout = "2020-08-15";
        String strCheckout = checkOut;
        System.out.println("DATA CHECK-OUT: " + strCheckout);

        // instanciando o LocalDate
        LocalDate checkin = LocalDate.now();
        LocalDate checkout = LocalDate.now();

        BookingInfo bookingInfo = new BookingInfo();

        // valida o formato da strind eh valido
        if(formatValidation(strCheckin, strCheckout)){
            // transforma a string em LocalDate
            checkin = string2LocalDate(strCheckin);
            checkout = string2LocalDate(strCheckout);
        } else {
            System.out.println("Formato de data invalido!! As datas devem ter o formato de YYYY-MM-DD");
            System.exit(0);
        }

        // valida se o checkIn é maior que o checkout
        if(checkin.isBefore(checkout)){
            //conta as semanas e dias adicionais
            bookingInfo = countWeeks(checkin, checkout);
        } else {
            System.out.println("Data de checkIn deve ser anterior a data de checkout!!");
            System.exit(0);
        }

        System.out.println(bookingInfo.toString());
    }

    /**
     * Metodo que verifica a diferença entre duas datas informadas e retorna em numero de semnas e dias extra.
     * 1 - Verifica se as datas sao menos que uma semana e completa a semana baseada no domingo
     *
     *
     *
     * Retorna o objeto BookingInfo que contem as informações necessarias
     * @param checkIn
     * @param checkOut
     * @return BookingInfo
     */
    public static BookingInfo countWeeks(LocalDate checkIn, LocalDate checkOut){

        Integer weeks = 0;
        Integer daysafter = 0;
        Integer daysBefore = 0;


        //Period period = Period.between(checkIn, checkOut);
        //int days = period.getDays();
        int days = (int)ChronoUnit.DAYS.between(checkIn, checkOut);


        // verifica dias extra
        Integer extraDays = days % 7;

        // 1 -  se tiver 4 ou menos diarias pega a semana
        if(days >= 4 && days < 7){
            LocalDate[] datesOneWeek = completeWeek(checkIn,  checkOut);
//            period = Period.between(datesOneWeek[0],datesOneWeek[1]);
//            days = period.getDays();

            days = (int)ChronoUnit.DAYS.between(datesOneWeek[0],datesOneWeek[1]);
            checkIn = datesOneWeek[0];
            checkOut = datesOneWeek[1];

            // numero de semanas
            weeks = days / 7;
            daysBefore = 0;
            daysafter = 0;
            return new BookingInfo(checkIn, checkOut);

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
                    daysafter = extraDays;
                }

            } else { // inicio = quarta ou quinta ou sexta -> semana de sabado a sabado
                // volta checkin para SABADO anterior

//                while (!checkIn.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
//                    checkIn = checkIn.plusDays(1);
//                }

                days = (int)ChronoUnit.DAYS.between(checkIn, checkOut);
                // numero de semanas
                weeks = days / 7;
                daysafter = 0;
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
                    daysafter = extraDays;
                }

        }


        // calculo final para retorno
        //weeks = days / 7;
        //daysafter = days % 7;


        return new BookingInfo(checkIn, checkOut);
    }


    // completa a semana se tiver menos de 4 diarias
    public static LocalDate[]  completeWeek(LocalDate start, LocalDate finish){

        LocalDate[] dates = new LocalDate[2];

        // StartDate
        LocalDate checkIn = start;
        while(!checkIn.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            checkIn = checkIn.minusDays(1);
        }
        dates[0] = checkIn;

        //FinishDate
        LocalDate checkout = finish;
        while (!checkout.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            checkout = checkout.plusDays(1);
        }
        dates[1] = checkout;

//        System.out.println("star date  : " + start);
//        System.out.println("finish date: " + finish);
//        System.out.println("check in   : " + checkIn);
//        System.out.println("check out  : " + checkout);

        return dates;
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
