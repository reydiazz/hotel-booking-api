package com.hotel.booking.api.domain.reservation.repository;

import com.hotel.booking.api.domain.reservation.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
