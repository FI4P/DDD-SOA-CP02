package com.enzo.fiap.hotel_booking.repository;

import com.enzo.fiap.hotel_booking.domain.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
    Optional<Room> findByNumber(int roomNumber);
}
