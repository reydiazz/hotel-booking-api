package com.hotel.booking.api.domain.reservation.model.entity;

import com.hotel.booking.api.domain.auth.model.entity.User;
import com.hotel.booking.api.domain.hotel.model.entity.Room;
import com.hotel.booking.api.domain.person.model.entity.Customer;
import com.hotel.booking.api.domain.reservation.exception.*;
import com.hotel.booking.api.domain.reservation.model.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservations")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Reservation {

    @Id
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_code", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_code", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReservationStatus status = ReservationStatus.PENDING;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationRoom> rooms = new ArrayList<>();

    public Reservation(String code, Customer customer, User user) {
        this.code = code;
        this.customer = customer;
        this.user = user;
        this.totalAmount = BigDecimal.ZERO;
    }

    public void addRoom(String code, Room room) {
        room.reserve();
        ReservationRoom reservationRoom = new ReservationRoom(code, this, room);
        this.rooms.add(reservationRoom);
    }

    public void checkIn(String reservationRoomCode) {
        ensureCanCheckIn();
        ReservationRoom reservationRoom = findRoomByCode(reservationRoomCode);
        reservationRoom.checkIn();
        this.status = ReservationStatus.ACTIVE;
    }

    public void checkOut(String reservationRoomCode) {
        ensureCanCheckOut();
        ReservationRoom reservationRoom = findRoomByCode(reservationRoomCode);
        reservationRoom.checkOut();
        if (allRoomsCheckedOut()) {
            this.status = ReservationStatus.DONE;
        }
    }

    public void cancel() {
        ensureCanCancel();
        this.rooms.forEach(reservationRoom -> reservationRoom.getRoom().markAvailable());
        this.status = ReservationStatus.CANCELLED;
    }

    private ReservationRoom findRoomByCode(String code) {
        return this.rooms.stream()
                .filter(room -> room.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new ReservationRoomNotFoundException(code)
        );
    }

    private boolean allRoomsCheckedOut() {
        return this.rooms.stream().allMatch(room -> room.getCheckOut() != null);
    }

    private void ensureCanCheckIn() {
        switch (this.status) {
            case CANCELLED -> throw new ReservationCancelledException("Reservation %s is cancelled and cannot perform check-in".formatted(this.code));
            case DONE -> throw new ReservationCompletedException(this.code);
        }
    }

    private void ensureCanCheckOut() {
        switch (this.status) {
            case CANCELLED -> throw new ReservationCancelledException("Reservation %s is cancelled and cannot perform check-out".formatted(this.code));
            case PENDING -> throw new ReservationCheckInRequiredException(this.code);
            case DONE -> throw new ReservationCompletedException(this.code);
        }
    }

    private void ensureCanCancel() {
        switch (this.status) {
            case CANCELLED -> throw new ReservationCancelledException("Reservation %s is already cancelled".formatted(this.code));
            case DONE ->throw new ReservationCompletedException(this.code);
        }
    }
}