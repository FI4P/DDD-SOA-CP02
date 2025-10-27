package com.enzo.fiap.hotel_booking.mapper;

import com.enzo.fiap.hotel_booking.domain.reservation.Reservation;
import com.enzo.fiap.hotel_booking.domain.reservation.ReservationStatus;
import com.enzo.fiap.hotel_booking.domain.room.Room;
import com.enzo.fiap.hotel_booking.dto.ReservationRequestDto;
import com.enzo.fiap.hotel_booking.dto.ReservationResponseDto;
import com.enzo.fiap.hotel_booking.dto.RoomResponseDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ReservationMapper {

    public ReservationResponseDto toResponse(Reservation reservation, boolean includeRoom){
        RoomResponseDto roomResponseDto = null;

        if(includeRoom){
            Room room = reservation.getRoom();
            roomResponseDto = RoomResponseDto.builder()
                    .id(room.getId())
                    .type(room.getType())
                    .capacity(room.getCapacity())
                    .pricePerNight(room.getPrice_per_night())
                    .status(room.getStatus())
                    .reservations(null)
                    .build();
        }

        return ReservationResponseDto.builder().id(reservation.getId())
                .status(reservation.getStatus())
                .checkinExpected(reservation.getCheckinExpected())
                .checkoutExpected(reservation.getCheckoutExpected())
                .guestName(reservation.getGuestName())
                .totalAmount(reservation.getTotalAmount())
                .room(roomResponseDto).build();
    }


    public Reservation toEntity(ReservationRequestDto reservationRequestDto, Room room, BigDecimal totalAmount){

        ReservationStatus reservationStatus = ReservationStatus.CREATED;

        return Reservation.builder().id(null).guestName(reservationRequestDto.guestName())
                .checkinExpected(reservationRequestDto.checkinExpected())
                .checkoutExpected(reservationRequestDto.checkoutExpected())
                .status(reservationStatus)
                .totalAmount(totalAmount)
                .room(room).build();
    }

    public Reservation toEntity(ReservationRequestDto reservationRequestDto, Room room, ReservationStatus status, BigDecimal totalAmount){

        ReservationStatus reservationStatus = ReservationStatus.CREATED;

        if(status != null){
            reservationStatus = status;
        }

        return Reservation.builder().id(null).guestName(reservationRequestDto.guestName())
                .checkinExpected(reservationRequestDto.checkinExpected())
                .checkoutExpected(reservationRequestDto.checkoutExpected())
                .status(reservationStatus)
                .totalAmount(totalAmount)
                .room(room).build();
    }

}
