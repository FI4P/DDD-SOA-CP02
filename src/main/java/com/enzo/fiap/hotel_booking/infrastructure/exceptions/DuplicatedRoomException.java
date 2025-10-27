package com.enzo.fiap.hotel_booking.infrastructure.exceptions;

public class DuplicatedRoomException extends RuntimeException {
    public DuplicatedRoomException() {
        super("Já existe uma quarto com esse número!");
    }
}
