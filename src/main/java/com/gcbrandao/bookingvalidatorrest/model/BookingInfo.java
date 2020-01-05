package com.gcbrandao.bookingvalidatorrest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingInfo {

    @NotNull
    LocalDate checkin;
    @NotNull
    LocalDate checkout;

    public BookingInfo() {
    }

    public BookingInfo(LocalDate checkin, LocalDate checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }


    public LocalDate getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();

        str.append("{").append("\n")
                .append("checkin:").append(this.getCheckin().toString()).append("\n")
                .append("checkout:").append(this.getCheckout().toString()).append("\n")
                .append("}");

        return str.toString();
    }
}
