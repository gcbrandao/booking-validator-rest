package com.gcbrandao.bookingvalidatorrest.resource;


import com.gcbrandao.bookingvalidatorrest.exceptionhandler.BookingExceptionHandler;
import com.gcbrandao.bookingvalidatorrest.model.BookingInfo;
import com.gcbrandao.bookingvalidatorrest.model.BookingReturn;
import com.gcbrandao.bookingvalidatorrest.service.BookingService;
import com.gcbrandao.bookingvalidatorrest.service.exception.BookingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingResource {


    @Autowired
    BookingService bookingService;

    @Autowired
    private MessageSource messageSource;

    @PostMapping
    public  ResponseEntity<BookingReturn> getWeeksAndExtraNigths(@Valid  @RequestBody BookingInfo bookingInfo,
                                                                 HttpServletResponse response){

        BookingReturn bookingInfoReturn = bookingService.getWeeksAndExtraNigths(bookingInfo);

        return ResponseEntity.status(HttpStatus.OK).body(bookingInfoReturn);
    }

    @ExceptionHandler({BookingException.class})
    public ResponseEntity<Object> hadleBookingException(BookingException ex) {

        final String mensagemUsuario = ex.getMessage();
        final String mensagemDev = ExceptionUtils.getRootCauseMessage(ex);

        final List<BookingExceptionHandler.Erro> erros = Arrays.asList(new BookingExceptionHandler.Erro(mensagemUsuario, mensagemDev));

        return ResponseEntity.badRequest().body(erros);
    }

}
