package com.enzo.fiap.hotel_booking.infrastructure.exceptions;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException() {
        super("Quarto não encontrado!");
    }
}
