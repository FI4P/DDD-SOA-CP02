package com.enzo.fiap.hotel_booking.infrastructure.exceptions;

public class BookedRoomException extends RuntimeException {
    public BookedRoomException() {
        super("Já existe uma reseva do quarto no período selecionado!");
    }
}
