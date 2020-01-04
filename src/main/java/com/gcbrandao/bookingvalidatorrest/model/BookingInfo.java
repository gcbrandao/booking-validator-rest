package com.gcbrandao.bookingvalidatorrest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingInfo {
    Integer weeks;

    Integer daysAfter;
    Integer daysBefore;
    LocalDate checkin;
    LocalDate checkout;

    public BookingInfo() {
    }

    public BookingInfo(Integer weeks, Integer daysAfter, Integer daysBefore, LocalDate checkin, LocalDate checkout) {
        this.weeks = weeks;
        this.daysAfter = daysAfter;
        this.daysBefore = daysBefore;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Integer getWeeks() {
        return weeks;
    }

    public void setWeeks(Integer weeks) {
        this.weeks = weeks;
    }

    public Integer getDaysAfter() {
        return daysAfter;
    }

    public void setDaysAfter(Integer daysAfter) {
        this.daysAfter = daysAfter;
    }

    public Integer getDaysBefore() {
        return daysBefore;
    }

    public void setDaysBefore(Integer daysBefore) {
        this.daysBefore = daysBefore;
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
                .append("noitesExtrasComeco:").append(this.getDaysBefore()).append("\n")
                .append("semanas:").append(this.getWeeks()).append("\n")
                .append("noitesExtrasFim:").append(this.getDaysAfter()).append("\n")
                .append("checkin:").append(this.getCheckin().toString()).append("\n")
                .append("checkout:").append(this.getCheckout().toString()).append("\n")
                .append("}");

        return str.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingInfo that = (BookingInfo) o;
        return Objects.equals(weeks, that.weeks) &&
                Objects.equals(daysAfter, that.daysAfter) &&
                Objects.equals(daysBefore, that.daysBefore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weeks, daysAfter, daysBefore);
    }


}
