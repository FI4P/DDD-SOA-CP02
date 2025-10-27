package com.enzo.fiap.hotel_booking.dto;

import com.enzo.fiap.hotel_booking.domain.room.RoomStatus;
import com.enzo.fiap.hotel_booking.domain.room.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record RoomRequestDto(

        @NotNull(message = "Informe o número do quarto!")
        @NotBlank(message = "Informe o número do quarto!")
        Integer number,

        @NotNull(message = "Informe o tipo do quarto!")
        @NotBlank(message = "Informe o tipo do quarto!")
        RoomType type,

        @NotNull(message = "Informe o limite de capacidade do quarto!")
        @NotBlank(message = "Informe o limite de capacidade do quarto!")
        @Size(min = 1, max = 6, message = "O quarto comporta até seis pessoas e no mínimo uma!")
        Integer capacity,

        @PositiveOrZero
        @NotNull(message = "Informe o preço do quarto!")
        BigDecimal pricePerNight,

        RoomStatus status
) {
}
