package com.enzo.fiap.hotel_booking.controller;


import com.enzo.fiap.hotel_booking.domain.reservation.ReservationStatus;
import com.enzo.fiap.hotel_booking.dto.ReservationRequestDto;
import com.enzo.fiap.hotel_booking.dto.ReservationResponseDto;
import com.enzo.fiap.hotel_booking.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    public final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @PostMapping
    public ResponseEntity<ReservationResponseDto> create(@Valid @RequestBody ReservationRequestDto body){

        ReservationResponseDto newReservation = reservationService.create(body);

        URI uri = URI.create("/reservations/" + newReservation.id());

        return ResponseEntity.created(uri).body(newReservation);

    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> index(){

        List<ReservationResponseDto> reservations = reservationService.index();
        return ResponseEntity.ok().body(reservations);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable UUID id, @RequestBody ReservationRequestDto dto){
        ReservationResponseDto updatedReservation = reservationService.updateStatus(id, dto.status());
        return ResponseEntity.ok().build();
    }

}
