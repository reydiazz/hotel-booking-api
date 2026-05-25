package com.hotel.booking.api.domain.reservation.model.entity;

import com.hotel.booking.api.domain.hotel.model.entity.Room;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "reservation_rooms")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReservationRoom {

    @Id
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "reservation_code", nullable = false)
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "room_code", nullable = false)
    private Room room;

    @Column(name = "price_per_night", nullable = false)
    private BigDecimal pricePerNight;

    @Column(name = "nights", nullable = false)
    private Integer nights;

    public ReservationRoom(String code, Reservation reservation, Room room, BigDecimal pricePerNight, Integer nights) {
        this.code = code;
        this.reservation = reservation;
        this.room = room;
        this.pricePerNight = pricePerNight;
        this.nights = nights;
    }

}
