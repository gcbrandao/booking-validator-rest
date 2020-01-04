package com.gcbrandao.bookingvalidatorrest.resource;


import com.gcbrandao.bookingvalidatorrest.model.BookingInfo;
import com.gcbrandao.bookingvalidatorrest.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/bookings")
public class BookingResource {


    @Autowired
    BookingService bookingService;

    @PostMapping
    public  ResponseEntity<BookingInfo> getWeeksAndExtraNigths( @RequestBody BookingInfo bookingInfo,
                                                                HttpServletResponse response){

        BookingInfo bookingInfoReturn = bookingService.getWeeksAndExtraNigths(bookingInfo);

        return ResponseEntity.status(HttpStatus.OK).body(bookingInfoReturn);
    }


}
