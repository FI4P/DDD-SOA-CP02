package com.enzo.fiap.hotel_booking.dto;

import com.enzo.fiap.hotel_booking.domain.room.RoomStatus;
import com.enzo.fiap.hotel_booking.domain.room.RoomType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record RoomResponseDto(
        UUID id,
        RoomType type,
        int capacity,
        BigDecimal pricePerNight,
        RoomStatus status,
        List<ReservationResponseDto> reservations
) {

}
