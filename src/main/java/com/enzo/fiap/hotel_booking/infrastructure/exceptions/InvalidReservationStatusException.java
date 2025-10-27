package com.enzo.fiap.hotel_booking.infrastructure.exceptions;

public class InvalidReservationStatusException extends RuntimeException {
    public InvalidReservationStatusException() {
        super("O status não pode ser atualizado!");
    }
}
