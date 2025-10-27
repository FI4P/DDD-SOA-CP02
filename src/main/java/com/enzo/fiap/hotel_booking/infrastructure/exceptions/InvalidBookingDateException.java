package com.enzo.fiap.hotel_booking.infrastructure.exceptions;

public class InvalidBookingDateException extends RuntimeException {
    public InvalidBookingDateException() {
        super("A data de checkin não pode ser posterior a data de checkout!");
    }
}
