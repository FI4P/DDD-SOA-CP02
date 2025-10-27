package com.enzo.fiap.hotel_booking.domain.reservation;


import com.enzo.fiap.hotel_booking.domain.room.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "reservations")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(nullable = false, name = "guest_name")
    private String guestName;

    @Column(nullable = false, name = "checkin_expected")
    private LocalDate checkinExpected;

    @Column(nullable = false, name = "checkout_expected")
    private LocalDate checkoutExpected;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Column(nullable = false, name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;

}
