package com.enzo.fiap.hotel_booking.repository;

import com.enzo.fiap.hotel_booking.domain.reservation.Reservation;
import com.enzo.fiap.hotel_booking.domain.reservation.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    @Query("""
                SELECT r 
                FROM Reservation r 
                WHERE r.room.id = :roomId
                  AND r.checkinExpected < :checkout
                  AND r.checkoutExpected > :checkin
            """)
    //Explicação para Query: https://excalidraw.com/#json=bH8dgv3T_Dw6JuNjciT5M,LRjVz8GtnpC9YaWcNgeZRw
    List<Reservation> findOverlappingReservations(
            @Param("roomId") UUID roomId,
            @Param("checkin") LocalDate checkin,
            @Param("checkout") LocalDate checkout);


    List<Reservation> findReservationByStatusNot(ReservationStatus status);
}
