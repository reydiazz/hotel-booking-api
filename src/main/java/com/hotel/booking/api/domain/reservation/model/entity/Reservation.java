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

    @Column(name = "check_in")
    private LocalDateTime checkIn;

    @Column(name = "check_out")
    private LocalDateTime checkOut;

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

    public void addRoom(String code, Room room, Integer nights) {
        room.reserve();
        ReservationRoom reservationRoom = new ReservationRoom(code, this, room, nights);
        this.rooms.add(reservationRoom);
        addToTotalAmount(room.getType().getBasePrice(), nights);
    }

    public void checkIn() {
        ensureCanCheckIn();
        this.rooms.forEach(reservationRoom -> reservationRoom.getRoom().occupy());
        this.status = ReservationStatus.ACTIVE;
        this.checkIn = LocalDateTime.now();
    }

    public void checkOut() {
        ensureCanCheckOut();
        this.rooms.forEach(reservationRoom -> reservationRoom.getRoom().markDirty());
        this.status = ReservationStatus.DONE;
        this.checkOut = LocalDateTime.now();
    }

    public void cancel() {
        ensureCanCancel();
        this.rooms.forEach(reservationRoom -> reservationRoom.getRoom().release());
        this.status = ReservationStatus.CANCELLED;
    }

    private void addToTotalAmount(BigDecimal pricePerNight, Integer nights) {
        BigDecimal subtotal = pricePerNight.multiply(BigDecimal.valueOf(nights));
        this.totalAmount = this.totalAmount.add(subtotal);
    }

    private void ensureCanCheckIn() {
        switch (this.status) {
            case CANCELLED ->
                    throw new ReservationCancelledException("Reservation %s is cancelled and cannot perform check-in".formatted(this.code));
            case ACTIVE -> throw new ReservationAlreadyCheckedInException(this.code);
            case DONE -> throw new ReservationCompletedException(this.code);
        }
    }

    private void ensureCanCheckOut() {
        switch (this.status) {
            case CANCELLED ->
                    throw new ReservationCancelledException("Reservation %s is cancelled and cannot perform check-out".formatted(this.code));
            case PENDING -> throw new ReservationCheckInRequiredException(this.code);
            case DONE -> throw new ReservationCompletedException(this.code);
        }
    }

    private void ensureCanCancel() {
        switch (this.status) {
            case CANCELLED ->
                    throw new ReservationCancelledException("Reservation %s is already cancelled".formatted(this.code));
            case DONE -> throw new ReservationCompletedException(this.code);
        }
    }
}