package com.hotel.booking.api.domain.reservation.model.entity;

import com.hotel.booking.api.domain.auth.model.entity.User;
import com.hotel.booking.api.domain.person.model.entity.Customer;
import com.hotel.booking.api.domain.reservation.exception.reservation.*;
import com.hotel.booking.api.domain.reservation.model.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    public Reservation(String code, Customer customer, User user) {
        this.code = code;
        this.customer = customer;
        this.user = user;
        this.checkIn = null;
        this.checkOut = null;
        this.totalAmount = BigDecimal.ZERO;
    }

    public void checkIn() {
        ensureCanCheckIn();
        this.status = ReservationStatus.ACTIVE;
        this.checkIn = LocalDateTime.now();
    }

    public void checkOut() {
        ensureCanCheckOut();
        this.status = ReservationStatus.DONE;
        this.checkOut = LocalDateTime.now();
    }

    public void cancel() {
        ensureCanCancel();
        this.status = ReservationStatus.CANCELLED;
    }

    public void defineTotalAmount(BigDecimal pricePerNight, Integer nights) {
        this.totalAmount = pricePerNight.multiply(BigDecimal.valueOf(nights));
    }

    public void validatePayment(BigDecimal paid, BigDecimal amount) {
        ensureCanPay();
        BigDecimal remaining = this.totalAmount.subtract(paid);
        if (amount.compareTo(remaining) > 0) {
            throw new ReservationPaymentExceedsBalanceException();
        }
    }

    private void ensureCanCheckIn() {
        switch (this.status) {
            case CANCELLED -> throw new ReservationCancelledException("Reservation %s is cancelled and cannot perform check-in".formatted(this.code));
            case ACTIVE -> throw new ReservationAlreadyCheckedInException(this.code);
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
            case DONE -> throw new ReservationCompletedException(this.code);
        }
    }

    private void ensureCanPay() {
        if (this.status == ReservationStatus.CANCELLED) {
            throw new ReservationCancelledException("Reservation %s is cancelled and cannot receive payments".formatted(this.code));
        }
    }

}
