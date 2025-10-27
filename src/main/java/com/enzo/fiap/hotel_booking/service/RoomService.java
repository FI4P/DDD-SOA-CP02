package com.enzo.fiap.hotel_booking.service;


import com.enzo.fiap.hotel_booking.domain.room.Room;
import com.enzo.fiap.hotel_booking.dto.RoomRequestDto;

import com.enzo.fiap.hotel_booking.dto.RoomResponseDto;
import com.enzo.fiap.hotel_booking.infrastructure.exceptions.DuplicatedRoomException;
import com.enzo.fiap.hotel_booking.mapper.RoomMapper;
import com.enzo.fiap.hotel_booking.repository.RoomRepository;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    public final RoomRepository roomRepository;
    public final RoomMapper roomMapper;

    public RoomService(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    public RoomResponseDto create(RoomRequestDto dto){
        Optional<Room> existingRoom = roomRepository.findByNumber(dto.number());

        existingRoom.ifPresent(room -> {
            throw new DuplicatedRoomException();
        });

        Room newRoom = roomMapper.toEntity(dto);
        roomRepository.save(newRoom);

        return roomMapper.toResponse(newRoom, false);
    }

    public List<RoomResponseDto> index(){
        List<RoomResponseDto> rooms = roomRepository.findAll().stream()
                .map(room -> roomMapper.toResponse(room, false))
                .toList();
        return rooms;
    }

}
