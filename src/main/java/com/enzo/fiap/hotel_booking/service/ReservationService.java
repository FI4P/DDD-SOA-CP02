package com.enzo.fiap.hotel_booking.service;

import com.enzo.fiap.hotel_booking.domain.reservation.Reservation;
import com.enzo.fiap.hotel_booking.domain.reservation.ReservationStatus;
import com.enzo.fiap.hotel_booking.domain.room.Room;
import com.enzo.fiap.hotel_booking.dto.ReservationRequestDto;
import com.enzo.fiap.hotel_booking.dto.ReservationResponseDto;
import com.enzo.fiap.hotel_booking.infrastructure.exceptions.*;
import com.enzo.fiap.hotel_booking.mapper.ReservationMapper;
import com.enzo.fiap.hotel_booking.repository.ReservationRepository;
import com.enzo.fiap.hotel_booking.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
public class ReservationService {

    public final ReservationRepository reservationRepository;
    public final RoomRepository roomRepository;
    public final ReservationMapper reservationMapper;
    public final Map<ReservationStatus, Set<ReservationStatus>> ALLOWED_TRANSITIONS = Map.of(
            ReservationStatus.CREATED, Set.of(ReservationStatus.CHECKED_IN, ReservationStatus.CANCELED),
            ReservationStatus.CHECKED_IN, Set.of(ReservationStatus.CHECKED_OUT, ReservationStatus.CANCELED),
            ReservationStatus.CHECKED_OUT, Set.of()
    );

    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.roomRepository = roomRepository;
    }

    public ReservationResponseDto create(ReservationRequestDto dto) {

        List<Reservation> overLappingReservations = reservationRepository.findOverlappingReservations(
                dto.roomId(),
                dto.checkinExpected(),
                dto.checkoutExpected());


        if(!overLappingReservations.isEmpty()){
            throw new BookedRoomException();
        }


        if (dto.checkinExpected().isAfter(dto.checkoutExpected())) {
            throw new InvalidBookingDateException();
        }

        Room room = roomRepository.findById(dto.roomId()).orElseThrow(RoomNotFoundException::new);

        BigDecimal bookedDays = BigDecimal.valueOf(ChronoUnit.DAYS.between(dto.checkinExpected(),dto.checkoutExpected()));
        BigDecimal totalAmount =  room.getPrice_per_night().multiply(bookedDays);

        Reservation newReservation = reservationMapper.toEntity(dto, room, totalAmount);

        reservationRepository.save(newReservation);

        return reservationMapper.toResponse(newReservation, true);

    }

    public List<ReservationResponseDto> index(){
        List<ReservationResponseDto> reservations = reservationRepository.findReservationByStatusNot(ReservationStatus.CANCELED).stream()
                .map(reservation -> reservationMapper.toResponse(reservation, true)).toList();

        return reservations;
    }

    public ReservationResponseDto updateStatus(UUID reservationId, ReservationStatus newStatus){
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(ReservationNotFoundException::new);

       if (reservation.getStatus().equals(newStatus)) return reservationMapper.toResponse(reservation, true);

       ReservationStatus currentStatus = reservation.getStatus();
       Set<ReservationStatus> validNextStatuses = ALLOWED_TRANSITIONS.getOrDefault(currentStatus, Set.of());

       if(!validNextStatuses.contains(newStatus)){
           throw new InvalidReservationStatusException();
       }

       reservation.setStatus(newStatus);
       reservationRepository.save(reservation);

       return reservationMapper.toResponse(reservation, true);

    }
}
