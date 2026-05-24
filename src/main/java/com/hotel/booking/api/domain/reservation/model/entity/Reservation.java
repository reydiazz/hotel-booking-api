package com.hotel.booking.api.domain.reservation.model.entity;

import com.hotel.booking.api.domain.auth.model.entity.User;
import com.hotel.booking.api.domain.person.model.entity.Customer;
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

    public Reservation(String code, Customer customer, User user){
        this.code = code;
        this.customer = customer;
        this.user = user;
        this.checkIn = null;
        this.checkOut = null;
        this.totalAmount = BigDecimal.ZERO;
    }

    public void defineCheckIn(){
        this.checkIn = LocalDateTime.now();
    }

    public void defineCheckOut(){
        this.checkOut = LocalDateTime.now();
    }

    public void addToTotalAmount(BigDecimal amount) {
        this.totalAmount = this.totalAmount.add(amount);
    }

    public void updateStatus(ReservationStatus status){
        this.status = status;
    }

}
