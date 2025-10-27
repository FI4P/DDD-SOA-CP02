package com.enzo.fiap.hotel_booking.infrastructure.exceptions;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException() {
        super("Reserva não encontrada!");
    }
}
