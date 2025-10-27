package com.enzo.fiap.hotel_booking.dto;

import com.enzo.fiap.hotel_booking.domain.reservation.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ReservationResponseDto(
        UUID id,
        String guestName,
        LocalDate checkinExpected,
        LocalDate checkoutExpected,
        ReservationStatus status,
        BigDecimal totalAmount,
        RoomResponseDto room
) {

}
