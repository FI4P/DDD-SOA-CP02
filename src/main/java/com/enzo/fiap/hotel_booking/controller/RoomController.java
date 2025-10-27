package com.enzo.fiap.hotel_booking.controller;


import com.enzo.fiap.hotel_booking.domain.reservation.Reservation;
import com.enzo.fiap.hotel_booking.dto.RoomRequestDto;
import com.enzo.fiap.hotel_booking.dto.RoomResponseDto;
import com.enzo.fiap.hotel_booking.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    public final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    @PostMapping
    public ResponseEntity<RoomResponseDto>create(@Valid @RequestBody RoomRequestDto body){
        RoomResponseDto newRoom = roomService.create(body);

        URI uri = URI.create("/rooms/" + newRoom.id());

        return ResponseEntity.created(uri).body(newRoom);
    }

    @GetMapping
    public ResponseEntity<List<RoomResponseDto>>index(){
        List<RoomResponseDto> rooms = roomService.index();
        return ResponseEntity.ok().body(rooms);
    }


}
