package com.enzo.fiap.hotel_booking.mapper;

import com.enzo.fiap.hotel_booking.domain.room.Room;
import com.enzo.fiap.hotel_booking.dto.ReservationResponseDto;
import com.enzo.fiap.hotel_booking.dto.RoomRequestDto;
import com.enzo.fiap.hotel_booking.dto.RoomResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomMapper {

    public RoomResponseDto toResponse(Room room, boolean includeReservations) {

        List<ReservationResponseDto> reservations = null;

        if (includeReservations && !room.getReservations().isEmpty()) {
            reservations = room.getReservations().stream().map(reservation -> new ReservationResponseDto(
                    reservation.getId(),
                    reservation.getGuestName(),
                    reservation.getCheckinExpected(),
                    reservation.getCheckoutExpected(),
                    reservation.getStatus(),
                    reservation.getTotalAmount(),
                    null)).toList();
        }

        return RoomResponseDto.builder().id(room.getId())
                .type(room.getType())
                .capacity(room.getCapacity())
                .pricePerNight(room.getPrice_per_night())
                .status(room.getStatus())
                .reservations(reservations)
                .build();

    }



    public Room toEntity(RoomRequestDto roomRequestDto){
        return Room.builder()
                .id(null)
                .number(roomRequestDto.number())
                .status(roomRequestDto.status())
                .type(roomRequestDto.type())
                .price_per_night(roomRequestDto.pricePerNight())
                .reservations(null)
                .capacity(roomRequestDto.capacity()).build();
    }

}
