package com.hotel.booking.api.domain.reservation.model.entity;

import com.hotel.booking.api.domain.hotel.model.entity.Room;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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

    @Column(name = "check_in")
    private LocalDateTime checkIn;

    @Column(name = "check_out")
    private LocalDateTime checkOut;

    public ReservationRoom(String code, Reservation reservation, Room room) {
        this.code = code;
        this.reservation = reservation;
        this.room = room;
        this.pricePerNight = room.getType().getBasePrice();
    }

    public void checkIn() {
        this.room.occupy();
        this.checkIn = LocalDateTime.now();
    }

    public void checkOut() {
        this.room.markDirty();
        this.checkOut = LocalDateTime.now();
    }

    public long getNights() {
        if (checkIn == null) {
            return 0;
        }
        LocalDate endDate = checkOut != null ? checkOut.toLocalDate() : LocalDate.now();
        return ChronoUnit.DAYS.between(checkIn.toLocalDate(), endDate);
    }

}