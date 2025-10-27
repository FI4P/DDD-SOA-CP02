package com.enzo.fiap.hotel_booking.dto;

import com.enzo.fiap.hotel_booking.domain.reservation.ReservationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


public record ReservationRequestDto(

        @NotNull(message = "Não foi possível identificar o quarto!")
        UUID roomId,

        @NotBlank(message = "Informe o nome do hospede!")
        @NotBlank(message = "Informe o nome do hospede!")
        String guestName,

        @NotNull(message = "Informe a data de Checkin!")
        LocalDate checkinExpected,

        @NotNull(message = "Informe a data de Checkout!")
        LocalDate checkoutExpected,

        ReservationStatus status,

        @Setter
        BigDecimal totalAmount
) {
}
