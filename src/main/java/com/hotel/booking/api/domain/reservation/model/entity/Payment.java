package com.hotel.booking.api.domain.reservation.model.entity;

import com.hotel.booking.api.domain.auth.model.entity.User;
import com.hotel.booking.api.domain.reservation.model.enums.PaymentMethod;
import com.hotel.booking.api.domain.reservation.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Payment {

    @Id
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_code", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_code", nullable = false)
    private Reservation reservation;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    public Payment(String code, User user, Reservation reservation, PaymentMethod method, PaymentStatus status, BigDecimal amount) {
        this.code = code;
        this.user = user;
        this.reservation = reservation;
        this.method = method;
        this.status = status;
        this.amount = amount;
        this.paymentDate = LocalDateTime.now();
    }

}
